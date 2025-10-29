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
import eterea.core.service.kotlin.exception.HabitacionMovimientoNotDeletableException;
import eterea.core.service.kotlin.exception.HabitacionNoDisponibleException;
import eterea.core.service.kotlin.exception.ReservaNoEditableException;
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

   public HabitacionMovimientoResponseDto findByNumeroReserva(Long numeroReserva) {
      HabitacionMovimiento habitacionMovimiento = repository.findByNumeroReserva(numeroReserva)
            .orElseThrow(() -> new HabitacionMovimientoExtendedException(numeroReserva));
      return habitacionMovimientoToDtoMapper.apply(habitacionMovimiento);
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
      Habitacion habitacion = habitacionService.findByNumero(dto.numeroHabitacion());

      HabitacionMovimiento reserva = new HabitacionMovimiento.Builder()
            .cliente(cliente)
            .fechaIngreso(dto.fechaIngreso())
            .fechaSalida(dto.fechaSalida())
            .habitacion(habitacion)
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
         habitacion.setClienteId(cliente.getClienteId());
         habitacionService.update(habitacion, habitacion.getNumero());
      }

      return habitacionMovimientoToDtoMapper.apply(savedReserva);

   }

   @Transactional
   public HabitacionMovimientoResponseDto updateReservaHabitacion(Long numeroReserva,
         CreateHabitacionMovimientoDto dto) {

      // OffsetDateTime fechaLimite; // TODO: Traer de la tabla violacionlimite (falta
      // mapear a una clase)

      HabitacionMovimiento existingReserva = repository.findByNumeroReserva(numeroReserva)
            .orElseThrow(() -> new HabitacionMovimientoExtendedException(numeroReserva));

      if (isHabitacionReservada(dto.numeroHabitacion(), dto.fechaIngreso(),
            dto.fechaSalida(),
            existingReserva.getHabitacionMovimientoId())) {
         throw new HabitacionNoDisponibleException(dto.numeroHabitacion(),
               dto.fechaIngreso(), dto.fechaSalida());
      }

      // Check if reservation can be updated
      if (existingReserva.getEstadoReserva().getLetraComprobante().equals("V")
            || existingReserva.getEstadoReserva().getLetraComprobante().equals("Z")) {
         throw new ReservaNoEditableException(existingReserva.getHabitacionMovimientoId(),
               existingReserva.getEstadoReserva().getLetraComprobante());
      }

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
      Habitacion habitacion = habitacionService.findByNumero(dto.numeroHabitacion());

      existingReserva.setCliente(cliente);
      existingReserva.setFechaIngreso(dto.fechaIngreso());
      existingReserva.setFechaSalida(dto.fechaSalida());
      existingReserva.setHabitacion(habitacion);
      existingReserva.setTarifaId(dto.tarifaId());
      existingReserva.setPrecioUnitarioTarifa(dto.precioUnitario());
      existingReserva.setCantidadPax(dto.cantidadPax().longValue());
      existingReserva.setCantidadPaxMayor(dto.paxMayor());
      existingReserva.setCantidadPaxMenor(dto.paxMenor());
      existingReserva.setTarifaStandard(dto.tarifaStandard() ? (byte) 1 : (byte) 0);
      existingReserva.setEstadoReserva(comprobanteService.findByModuloAndLetraComprobante(11, dto.letraComprobante()));
      existingReserva.setFechaOperacion(dto.fechaOperacion());
      existingReserva.setFechaVencimiento(dto.fechaVencimiento());
      existingReserva.setObservaciones(dto.observaciones());
      existingReserva.setConceptoTarifa("");
      existingReserva.setPrecioUnitarioTarifa(BigDecimal.ZERO);
      existingReserva.setTarifaId(0L);

      HabitacionMovimiento savedReserva = save(existingReserva);
      if (savedReserva.getEstadoReserva().getLetraComprobante().equals("R")) {
         habitacion.setClienteId(cliente.getClienteId());
         habitacionService.update(habitacion, habitacion.getNumero());
      }

      return habitacionMovimientoToDtoMapper.apply(savedReserva);
   }

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
      HabitacionMovimiento habitacionMovimiento = repository.findByNumeroReserva(habitacionMovimientoId)
            .orElseThrow(() -> new HabitacionMovimientoExtendedException(habitacionMovimientoId));
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

   public void delete(Long numeroReserva) {
      HabitacionMovimiento habitacionMovimiento = repository.findByNumeroReserva(numeroReserva)
            .orElseThrow(() -> new HabitacionMovimientoExtendedException(numeroReserva));
      if (!habitacionMovimiento.getEstadoReserva().getLetraComprobante().equals("A")) {
         throw new HabitacionMovimientoNotDeletableException(habitacionMovimiento.getNumeroReserva(),
               habitacionMovimiento.getEstadoReserva().getLetraComprobante());
      }
      repository.delete(habitacionMovimiento);
   }

   public List<Habitacion> getHabitacionesDisponibles(OffsetDateTime desde, OffsetDateTime hasta,
         Long habitacionMovimientoIdExcluir) {
      List<Habitacion> allHabitaciones = habitacionService.findAll();

      return allHabitaciones.stream()
            .filter(habitacion -> !isHabitacionReservada(
                  habitacion.getNumero(),
                  desde,
                  hasta,
                  habitacionMovimientoIdExcluir))
            .toList();
   }

}

/*
 * Estados de Reserva
 * R -> Ocupada
 * A -> Pendiente
 * N -> Pagada
 * V -> Concretada
 * Z -> De_Baja
 * O -> Facturada
 * B -> Especial
 */
