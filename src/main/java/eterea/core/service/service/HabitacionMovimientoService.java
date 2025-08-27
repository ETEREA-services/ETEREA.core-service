package eterea.core.service.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import eterea.core.service.exception.ClienteException;
import eterea.core.service.exception.view.HabitacionMovimientoExtendedException;
import eterea.core.service.kotlin.exception.HabitacionNoDisponibleException;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.Habitacion;
import eterea.core.service.kotlin.model.HabitacionMovimiento;
import eterea.core.service.kotlin.repository.HabitacionMovimientoRepository;
import eterea.core.service.model.dto.HabitacionMovimientoResponseDto;
import eterea.core.service.model.dto.CreateHabitacionMovimientoDto;
import eterea.core.service.model.dto.mapper.HabitacionMovimientoToDtoMapper;
import jakarta.transaction.Transactional;

@Service
public class HabitacionMovimientoService {

    private final HabitacionMovimientoRepository repository;
    private final ClienteService clienteService;
    private final HabitacionService habitacionService;
    private final ComprobanteService comprobanteService;
    private final HabitacionMovimientoToDtoMapper habitacionMovimientoToDtoMapper;
    private final Logger log = LoggerFactory.getLogger(HabitacionMovimientoService.class);

    public HabitacionMovimientoService(HabitacionMovimientoRepository repository, ClienteService clienteService,
            HabitacionService habitacionService, ComprobanteService comprobanteService,
            HabitacionMovimientoToDtoMapper habitacionMovimientoToDtoMapper) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.habitacionService = habitacionService;
        this.comprobanteService = comprobanteService;
        this.habitacionMovimientoToDtoMapper = habitacionMovimientoToDtoMapper;
    }

    public List<HabitacionMovimiento> findReservasSuperpuestas(
            Integer numeroHabitacion,
            OffsetDateTime fechaIngreso,
            OffsetDateTime fechaSalida,
            Long idExcluir) {

        OffsetDateTime fechaIngresoMasUnDia = fechaIngreso.plusDays(1);
        OffsetDateTime fechaSalidaMenosUnDia = fechaSalida.minusDays(1);
        return repository.findReservasSuperpuestas(
                numeroHabitacion,
                fechaIngreso,
                fechaSalida,
                fechaIngresoMasUnDia,
                fechaSalidaMenosUnDia,
                idExcluir != null ? idExcluir : 0L);

    }

    public HabitacionMovimiento save(HabitacionMovimiento habitacionMovimiento) {
        return repository.save(habitacionMovimiento);
    }

    public Optional<HabitacionMovimiento> findById(Long habitacionMovimientoId) {
        return repository.findById(habitacionMovimientoId);
    }

    public HabitacionMovimiento findByNumeroReserva(Long numeroReserva) {
        return repository.findByNumeroReserva(numeroReserva)
                .orElseThrow(() -> new HabitacionMovimientoExtendedException(numeroReserva));
    }

    @Transactional
    public HabitacionMovimientoResponseDto createReservaHabitacion(CreateHabitacionMovimientoDto dto) {
        boolean isAgencia = dto.cuit() != null
                && !dto.cuit().equals("00-00000000-0")
                && !dto.cuit().isBlank();

        Cliente cliente = null;
        if (isAgencia) {
            cliente = clienteService.findByCuit(dto.cuit());
        } else {
            try {
                cliente = clienteService.findByNumeroDocumentoAndDocumentoId(
                        dto.nroDocumento(),
                        dto.tipoDocumentoId());
            } catch (ClienteException e) {
                cliente = clienteService.findByNumeroDocumento(dto.nroDocumento());
            }
        }
        // Check room availability
        if (isHabitacionReservada(dto.numeroHabitacion(), dto.fechaIngreso(), dto.fechaSalida(), null)) {
            throw new HabitacionNoDisponibleException(dto.numeroHabitacion(), dto.fechaIngreso(), dto.fechaSalida());
        }

        HabitacionMovimiento reserva = new HabitacionMovimiento.Builder()
                .cliente(cliente)
                .fechaIngreso(dto.fechaIngreso())
                .fechaSalida(dto.fechaSalida())
                .habitacion(habitacionService.findByNumero(dto.numeroHabitacion()))
                .tarifaId(dto.tarifaId())
                .precioUnitarioTarifa(dto.precioUnitario())
                .cantidadPax(dto.cantidadPax().longValue())
                .cantidadPaxMayor(dto.paxMayor())
                .cantidadPaxMenor(dto.paxMenor())
                .tarifaStandard(dto.tarifaStandard() ? (byte) 1 : (byte) 0)
                .estadoReserva(comprobanteService.findByModuloAndLetraComprobante(11, dto.letraComprobante()))
                .fechaOperacion(dto.fechaOperacion())
                .fechaVencimiento(dto.fechaVencimiento())
                .observaciones(dto.observaciones())
                .conceptoTarifa("")
                .precioUnitarioTarifa(BigDecimal.ZERO)
                .tarifaId(0L)
                .build();

        HabitacionMovimiento lastHabitacionMovimiento = findLastHabitacionMovimiento();
        Long lastHabitacionMovimientoId = lastHabitacionMovimiento != null
                ? lastHabitacionMovimiento.getHabitacionMovimientoId()
                : null;

        reserva.setHabitacionMovimientoId(lastHabitacionMovimientoId + 1);
        reserva.setNumeroReserva(lastHabitacionMovimiento.getNumeroReserva() + 1);

        HabitacionMovimiento savedReserva = save(reserva);

        if (savedReserva.getEstadoReserva().getLetraComprobante().equals("R")) {
            Habitacion habitacion = habitacionService.findByNumero(dto.numeroHabitacion());
            habitacion.setClienteId(cliente.getClienteId());
            habitacionService.update(habitacion, habitacion.getNumero());
        }

        return habitacionMovimientoToDtoMapper.apply(savedReserva);

    }

    // @Transactional
    // public HabitacionMovimiento updateReservaHabitacion(Long
    // habitacionMovimientoId, ReservaHotelDto dto) {
    //
    // OffsetDateTime fechaLimite; // TODO: Traer de la tabla violacionlimite (falta
    // mapear a una clase)
    //
    // // 1. Find existing reservation
    // HabitacionMovimiento existingReserva = findById(habitacionMovimientoId)
    // .orElseThrow(() -> new ReservaException(habitacionMovimientoId));
    //
    // // 2. Validate operation date
    // validarFechaOperacion(dto.fechaOperacion());
    //
    // // 3. Validate future date limit
    // validarPlazoFuturo(dto.fechaOperacion());
    //
    // // 4. Validate required fields
    // validarCamposRequeridos(dto);
    //
    // // 5. Validate dates
    // validarFechas(dto.fechaIngreso(), dto.fechaSalida());
    //
    // // 6. Validate PAX counts
    // validarCantidadPax(dto.paxMayor(), dto.paxMenor(), dto.cantidadPax());
    //
    // // 7. Check room availability (excluding current reservation)
    // if (isHabitacionReservada(dto.numeroHabitacion(), dto.fechaIngreso(),
    // dto.fechaSalida(),
    // habitacionMovimientoId)) {
    // throw new HabitacionNoDisponibleException(dto.numeroHabitacion(),
    // dto.fechaIngreso(), dto.fechaSalida());
    // }
    //
    // // Check if reservation can be updated
    // if (existingReserva.getEstadoReserva().getLetraComprobante().equals("V")
    // || existingReserva.getEstadoReserva().getLetraComprobante().equals("A")) {
    // throw new
    // ReservaNoEditableException(existingReserva.getHabitacionMovimientoId(),
    // existingReserva.getEstadoReserva().getLetraComprobante());
    // }
    //
    // // 8. Update the reservation
    // //
    // existingReserva.setCliente(clienteService.findByClienteId(dto.clienteId()));
    // existingReserva.setHabitacion(habitacionService.findByNumero(dto.numeroHabitacion()));
    // existingReserva.setFechaIngreso(dto.fechaIngreso());
    // existingReserva.setFechaSalida(dto.fechaSalida());
    // existingReserva.setCantidadPax(dto.cantidadPax().longValue());
    // existingReserva.setCantidadPaxMayor(dto.paxMayor());
    // existingReserva.setCantidadPaxMenor(dto.paxMenor());
    // existingReserva.setFechaOperacion(dto.fechaOperacion());
    // existingReserva.setFechaVencimiento(dto.fechaVencimiento());
    // existingReserva.setTarifaId(dto.tarifaId());
    // existingReserva.setPrecioUnitarioTarifa(dto.precioUnitario());
    // existingReserva.setTarifaStandard(dto.tarifaStandard() ? (byte) 1 : (byte)
    // 0);
    // existingReserva.setObservaciones(dto.observaciones());
    //
    // return save(existingReserva);
    // }

    public HabitacionMovimiento findLastHabitacionMovimiento() {
        return repository.findFirstByOrderByHabitacionMovimientoIdDesc();
    }

    public boolean isHabitacionReservada(Integer numeroHabitacion, OffsetDateTime fechaIngreso,
            OffsetDateTime fechaSalida, Long reservaIdExcluir) {
        List<HabitacionMovimiento> reservasSuperpuestas = findReservasSuperpuestas(
                numeroHabitacion,
                fechaIngreso,
                fechaSalida,
                reservaIdExcluir);

        return !reservasSuperpuestas.isEmpty();
    }

    public void appendToObservaciones(Long habitacionMovimientoId, String observacion) {
        HabitacionMovimiento habitacionMovimiento = findByNumeroReserva(habitacionMovimientoId);
        log.info("Observación a agregar: {}", observacion);
        log.info("Observación actual: {}", habitacionMovimiento.getObservaciones());
        if (habitacionMovimiento.getObservaciones().isEmpty()) {
            habitacionMovimiento.setObservaciones(observacion);
        } else {
            habitacionMovimiento.setObservaciones(habitacionMovimiento.getObservaciones() + "\n" + observacion);
        }
        var savedReserva = save(habitacionMovimiento);
        log.info("Reserva guardada: {}", savedReserva);
    }

    public HabitacionMovimientoResponseDto getHabitacionMovimientoByNroReserva(Long nroReserva) {
        Optional<HabitacionMovimiento> habitacionMovimientoOpt = repository.findByNumeroReserva(nroReserva);
        if (!habitacionMovimientoOpt.isPresent()) {
            throw new HabitacionMovimientoExtendedException(nroReserva);
        }
        return habitacionMovimientoToDtoMapper.apply(habitacionMovimientoOpt.get());
    }
}
