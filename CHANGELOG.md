## [2.5.2] - 2026-07-14

### Fixed
- **fix(cuentamovimiento-mapper)**: Null-safety en `CuentaMovimientoMapper.toEntity()` — 16 campos con null-checks y valores por defecto para prevenir NPEs (`orden`, `item`, `debita`, `negocioId`, `numeroCuenta`, `comprobanteId`, `concepto`, `importe`, `subrubroId`, `proveedorId`, `clienteId`, `cierreCajaId`, `nivel`, `firma`, `tipoAsientoId`, `articuloMovimientoId`, `inflacion`)
- **fix(stockmovimiento-mapper)**: Null-safety en `StockMovimientoMapper.toEntity()` — 10 campos con null-checks y valores por defecto (`numeroComprobanteInterno`, `centroStockIdHastaNombre`, `importe`, `nivel`, `generacionAutomatica`, `pendiente`, `rechazada`, `facturaProveedor`, `netoFactura`, `netoRegistrado`)
- **fix(valormovimiento-mapper)**: Null-safety en `ValorMovimientoMapper.toEntity()` — 5 campos con null-checks y valores por defecto (`importe`, `ordenContable`, `tanda`, `tandaIndex`, `nivel`)
- **fix(clientemovimiento-mapper)**: Null-safety en `ClienteMovimientoMapper.toEntity()` — 25 campos con null-checks y valores por defecto para prevenir NPEs en mapeo domain→entity

## [2.5.1] - 2026-07-13

### Features
- **feat(articulomovimiento-dto)**: Ampliación de `ArticuloMovimientoResponse` con 14 campos nuevos: `tenenciaMovimientoId`, `numeroCuenta`, `iva105`, `exento`, `fechaFactura`, `nivel`, `cierreCajaId`, `cierreRestaurantId`, `precioCompra`, `precioValuacion`, `mozoId`, `comision`, `trackUuid` y `articulo` (objeto `ArticuloResponse`)
- **feat(cierrecaja)**: Enriquecimiento de `PendingCountsDtoMapper` para poblar `articuloMovimientos` en `ClienteMovimientoResponse` y `StockMovimientoResponse` al construir `PendingCountsResponse` — los conteos pendientes de cierre de caja ahora incluyen los movimientos de artículos asociados
- **feat(stockmovimiento-dto)**: Nuevo campo `articuloMovimientos: List<ArticuloMovimientoResponse>` en `StockMovimientoResponse`
- **feat(clientemovimiento-dto)**: Nuevo campo `articuloMovimientos: List<ArticuloMovimientoResponse>` en `ClienteMovimientoResponse`

### Changed
- **refactor(articulomovimiento)**: `ArticuloMovimiento` implementa interfaz `Jsonifyable` en lugar de declarar método propio `jsonify()` — uso de serialización JSON centralizada
- **refactor(articulomovimiento-mapper)**: `ArticuloMovimientoDtoMapper` migrado a `@RequiredArgsConstructor` con inyección de `ArticuloDtoMapper` para mapeo completo de nuevos campos

## [2.5.0] - 2026-07-12

### 🚀 Migración Masiva de Módulos a Arquitectura Hexagonal y Reorganización de Paquetes

#### Features
- **feat(cliente-movimiento)**: Nuevo módulo hexagonal completo `clienteMovimiento` con arquitectura hexagonal — dominio, puertos de entrada/salida, casos de uso (`Create`, `Delete`, `FindById`, `FindByComprobante`, `FindAsociables`, `FindByIds`, `FindByReserva`, `FindFacturados`, `FindNextNumeroComprobante`, `Update`), adaptador JPA, controlador REST, DTOs de respuesta/request y mappers
- **feat(cuenta-movimiento)**: Nuevo módulo hexagonal completo `cuentamovimiento` bajo `contable/` — dominio, puertos de entrada/salida, casos de uso (`Delete`, `FindById`, `FindByContable`, `FindByFechaRange`, `FindLastByAsiento`, `FindLastByFecha`, `Save`), adaptador JPA, controlador REST, DTOs y mappers
- **feat(valor-movimiento)**: Nuevo módulo hexagonal completo `valormovimiento` — dominio, puertos de entrada/salida, casos de uso (`Create`, `Delete`, `FindAll`, `FindByContable`, `SaveAll`, `Update`), adaptador JPA, controlador REST, DTOs y mappers
- **feat(stock-movimiento)**: Nuevo módulo hexagonal completo `stockmovimiento` bajo `stock/` — dominio, puertos de entrada/salida, casos de uso (`Create`, `FindById`, `FindLastByComprobanteId`), adaptador JPA, controlador REST, DTOs y mappers
- **feat(process-cierre)**: Nuevo módulo hexagonal `processCierre` bajo `cierrecaja/` — endpoint `GET` para obtener conteos pendientes (`PendingCounts`), adaptador JPA, controlador REST, DTO y mapper
- **feat(anticipo-haberes)**: Nuevo caso de uso `SaveAllAnticipoHaberesUseCase` y excepción `CierreCajaAnticipoHaberesException` en módulo `cierrecaja/anticipohaberes`
- **feat(compras)**: Nuevos puertos de entrada en `proveedormovimiento`: `GetProveedorMovimientosByProveedorIdUseCase`, `GetResumenIvaComprasMensualUseCase`, `UpdateProveedorMovimientoNetoAjusteUseCase`
- **feat(contable)**: Nuevos puertos de entrada para `cuenta`: `FindAllCuentasUseCase`, `FindAllCuentasWithCuentaMaestroUseCase`, `FindCuentaByCuentaMaestroUseCase`, `FindCuentaByNumeroCuentaUseCase`
- **feat(json)**: Nueva interfaz `Jsonifyable` para serialización JSON centralizada

### Changed
- **refactor(articulomovimiento)**: Migración de puertos de entrada de `hexagonal/articulomovimiento/` a `hexagonal/stock/articulomovimiento/` — `CreateArticuloMovimientoUseCase`, `GetArticuloMovimientoByIdUseCase`, `SaveAllArticuloMovimientosUseCase`
- **refactor(articulo)**: Migración completa del módulo `articulo` de `hexagonal/articulo/` a `hexagonal/stock/articulo/` — casos de uso, puertos, adaptador JPA, controlador, servicio, mapper y excepción
- **refactor(cuenta)**: Migración completa del módulo `cuenta` de `hexagonal/cuenta/` a `hexagonal/contable/cuenta/` — casos de uso, puertos, adaptador JPA, controlador, servicio, mapper y entidad
- **refactor(proveedormovimiento)**: Migración completa del módulo `proveedormovimiento` de `hexagonal/proveedormovimiento/` a `hexagonal/compras/proveedormovimiento/` — todos los componentes de infraestructura
- **refactor(comprobante)**: Relocalización de `ComprobanteException` a `hexagonal/comprobante/application/exception/` y renombrado de `JpaComprobanteRepositoryAdapter` a `hexagonal/comprobante/infrastructure/persistence/adapter/`
- **refactor(legajo)**: Relocalización de `LegajoException` a `hexagonal/legajo/application/exception/` y renombrado de `JpaLegajoRepositoryAdapter` a `hexagonal/legajo/infrastructure/persistence/adapter/`
- **refactor(invoicedata)**: Renombrado de todos los DTOs de respuesta con sufijo `InvoiceData` — `ArticuloMovimientoResponse` → `ArticuloMovimientoInvoiceDataResponse`, `ClienteResponse` → `ClienteInvoiceDataResponse`, `ClienteMovimientoResponse` → `ClienteMovimientoInvoiceDataResponse`, `ComprobanteAfipResponse` → `ComprobanteAfipInvoiceDataResponse`, `ComprobanteResponse` → `ComprobanteInvoiceDataResponse`, `ConceptoFacturadoResponse` → `ConceptoFacturadoInvoiceDataResponse`, `EmpresaResponse` → `EmpresaInvoiceDataResponse`, `MonedaResponse` → `MonedaInvoiceDataResponse`, `RegistroCaeResponse` → `RegistroCaeInvoiceDataResponse`
- **refactor(cierrecaja)**: Renombrado de `AnticipoHaberesMapper` a `CierreCajaAnticipoHaberesMapper` y renombrado de adaptador de `repository` a `adapter` en `anticipohaberes`
- **refactor(proveedor-movimiento)**: Movimiento de `ProveedorMovimientoService`, modelos, puertos, adaptador JPA, DTOs, mappers y controlador a paquete `compras/proveedormovimiento`
- **refactor(transferencia)**: Relocalización de `FindTransferenciaByUniqueUseCase` a `hexagonal/transferencia/domain/ports/in/`
- **refactor(cuenta-service)**: Relocalización de `CuentaMovimientoService` a `hexagonal/contable/cuentamovimiento/application/service/`
- **refactor(excepciones)**: Relocalización de `ClienteMovimientoException`, `CuentaMovimientoException` y `StockMovimientoException` a módulos hexagonales correspondientes

### Removed
- **chore(cleanup)**: Eliminación de controladores legacy: `ClienteMovimientoController.java`, `CuentaMovimientoController.java`, `ValorMovimientoController.java` en `controller/`
- **chore(cleanup)**: Eliminación de excepciones legacy: `ClienteMovimientoException.java`, `CuentaMovimientoException.java`, `StockMovimientoException.java` en `exception/`
- **chore(cleanup)**: Eliminación de repositorios y modelos Kotlin: `CuentaMovimientoRepository.kt`, `StockMovimientoRepository.kt`, `ValorMovimientoRepository.kt`, `StockMovimiento.kt`, `ValorMovimiento.kt`, `TransferenciaException.kt`
- **chore(cleanup)**: Eliminación de puertos de entrada Kotlin: `CreateArticuloUseCase`, `FindAllArticulosUseCase`, `FindArticuloByArticuloIdUseCase`, `FindArticuloByAutoNumericoUseCase`, `FindArticuloByMascaraBalanzaUseCase`, `FindArticulosBySearchUseCase`, `UpdateArticuloUseCase` en `hexagonal/articulo/domain/ports/in/`
- **chore(cleanup)**: Eliminación de `ClienteMovimientoMapper` en `invoicedata/infrastructure/mapper/`
- **chore(cleanup)**: Eliminación de `ValorMovimientoDtoMapper` en `model/dto/`
- **chore(cleanup)**: Eliminación de `ClienteMovimientoRepository` legacy en `repository/`
- **chore(cleanup)**: Eliminación de servicios legacy: `ClienteMovimientoService`, `StockMovimientoService`, `ValorMovimientoService` en `service/`
- **chore(cleanup)**: Eliminación de puertos de entrada legacy: `FindAllCuentasUseCase`, `FindAllCuentasWithCuentaMaestroUseCase`, `FindCuentaByCuentaMaestroUseCase`, `FindCuentaByNumeroCuentaUseCase` en `hexagonal/cuenta/domain/ports/in/`
- **chore(cleanup)**: Eliminación de puertos de entrada legacy en `hexagonal/proveedormovimiento/domain/ports/in/`: `GetProveedorMovimientosByProveedorIdUseCase`, `GetResumenIvaComprasMensualUseCase`, `UpdateProveedorMovimientoNetoAjusteUseCase`
- **chore(cleanup)**: Eliminación de `FindTransferenciaByUniqueUseCase` en `hexagonal/transferencia/domain/ports/in/`

## [2.4.5] - 2026-06-29

### Fixed
- **fix(reserva)**: Corregida la duplicación del artículo compartido en `ReservaService.generarReservaArticulo`. La colección `collectionAgregar` se construía con la clave `Articulo::toString`, que al no existir un `@ToString` en `Articulo` devuelve el hash de identidad del objeto (único por instancia)

## [2.4.4] - 2026-06-28

### Fixed
- **fix(json-serialization)**: Corregido patrón `@JsonFormat` de `yyyy-MM-dd'T'HH:mm:ssZ` a `yyyy-MM-dd'T'HH:mm:ssXX` en todas las entidades y modelos con `OffsetDateTime` para serialización ISO 8601 correcta con colon en el offset horario (afecta ~40 clases Java y Kotlin en los módulos: `articulomovimiento`, `cierrecaja`, `legajo`, `proveedormovimiento`, `transferencia`, `clienteweb`, `voucher`, `reserva`, `stock`, `valor`, `asiento`, `cliente`, `cuenta`, `inventario`, `feriado`, `moneda`, `comprobante`, `habitacion`, `producto`, `usuario`)

### Changed
- **refactor(logging)**: Eliminación de Log4j2 — removido `spring-boot-starter-logging` exclusion y dependencia `spring-boot-starter-log4j2` de `spring-boot-starter`, revirtiendo al logging default de Spring Boot (Logback)
- **refactor(deps)**: Actualización de Spring Boot 4.0.6 → 4.1.0 (`pom.xml`)
- **refactor(deps)**: Actualización de Spring Cloud 2025.1.0 → 2025.1.2 (`pom.xml`)
- **refactor(deps)**: Actualización de OpenPDF 3.0.3 → 3.0.5 (`pom.xml`)
- **refactor(deps)**: Actualización de MySQL Connector 9.6.0 → 9.7.0 (`pom.xml`)

## [2.4.3] - 2026-06-10

### Changed
- **refactor(controller)**: Sustitución de constructor manual por `@RequiredArgsConstructor` en `ArticuloBarraController`
- **refactor(service)**: Sustitución de constructor manual por `@RequiredArgsConstructor` en `ArticuloBarraService`
- **refactor(logging)**: Refactor de logging en `ArticuloBarraService` — reemplazo del método privado `logArticuloBarra()` (basado en `JsonMapper`) por `log.debug` con `jsonify()` inline, eliminando dependencias no utilizadas (`Objects`, `JsonMapper`, `JsonProcessingException`)

## [2.4.2] - 2026-06-07

### Fixed
- **fix(articulomovimiento)**: Corrección en `AutoCompleteTotalesByClienteMovimientoIdUseCaseImpl` para prevenir `totalConIva` negativo al usar el valor absoluto de `cantidad` (`movimiento.getCantidad().abs()`) en el cálculo de totales

### Docs
- **docs**: Nuevo diagrama de clases `articulomovimiento-class-diagram.mmd` para documentar el módulo `ArticuloMovimiento` y su infraestructura hexagonal

## [2.4.1] - 2026-06-06

### Fixed
- **fix(mapper)**: Null-safety improvements en `ArticuloMovimientoMapper` para campos `stockMovimientoId`, `tenenciaMovimientoId`, `nivel`, `cierreCajaId`, `cierreRestaurantId`, `mozoId`, `precioValuacion`, `comision`, `totalConIva` y `totalSinIva` — prevención de NPEs con valores nulos del dominio
- **fix(error-handling)**: Mejora en manejo de excepciones en `MakeFacturaProgramaDiaService` añadiendo captura genérica de `Exception` para errores imprevistos en envío de email

### Changed
- **refactor(controller)**: Sustitución de constructor manual por `@RequiredArgsConstructor` en `ConceptoFacturadoController`
- **refactor(mapper)**: Sustitución de constructor manual por `@RequiredArgsConstructor` en `ArticuloMovimientoMapper`
- **refactor(service)**: Sustitución de constructor manual por `@RequiredArgsConstructor` en `ConceptoFacturadoService`
- **refactor(service)**: Adición de `@Slf4j` en `GetInvoiceDataByClienteMovimientoIdImpl`
- **refactor(dto)**: Adición del método `jsonify()` en `RegistroCaeResponse` para serialización JSON consistente
- **refactor(logging)**: Mejora de mensajes de depuración con formato estructurado (`\n\n`) en `ArticuloMovimientoService`, `AutoCompleteTotalesByClienteMovimientoIdUseCaseImpl`, `GetInvoiceDataByClienteMovimientoIdImpl`, `InvoiceDataMapper`, `FacturacionService`, `MakeFacturaProgramaDiaService`, `VouchersService` y `ProductsService`

### Dependencies
- **chore(deps)**: Actualización de Kotlin 2.3.21 → 2.4.0 en `pom.xml`

## [2.4.0] - 2026-06-05

### Changed
- **refactor(negocio)**: Refactorización del modelo `Negocio` en el módulo `empresa`:
  - Nuevo campo `databasePort` añadido al modelo de dominio, entidad JPA, mapper, DTO de respuesta y DTO mapper
  - Eliminación de los campos `facturaServer` y `facturaPort` del modelo de dominio, entidad JPA y mapper
  - Reordenación del campo `backendServer` en `NegocioResponse` y `NegocioDtoMapper` para consistencia

### Docs
- **docs**: Nuevo diagrama de clases `negocio-class-diagram.mmd` para documentar el modelo `Negocio` y su infraestructura asociada

## [2.3.1] - 2026-05-30

### Changed
- **refactor(empresa)**: Refactorización completa del módulo `Empresa` para separar correctamente los casos de uso de la capa de servicio en arquitectura hexagonal
- **refactor(empresa)**: Extracción de `GetLastEmpresaUseCaseImpl` con la lógica de generación de UUID y persistencia desde `EmpresaService`
- **refactor(empresa)**: Renombrado de `EmpresaJpaRepository` → `JpaEmpresaRepository` y `EmpresaJpaRepositoryAdapter` → `JpaEmpresaRepositoryAdapter` para consistencia con el resto de módulos hexagonales
- **refactor(empresa)**: Simplificación de `EmpresaService` para que actúe como orquestador delegando en `GetLastEmpresaUseCase`
- **refactor(empresa)**: `EmpresaController` ahora inyecta `EmpresaService` en lugar de `GetLastEmpresaUseCase` directamente
- **test(empresa)**: Nuevos tests unitarios completos para `GetLastEmpresaUseCaseImpl` (3 casos: con businessId, sin businessId genera UUID, empresa no encontrada)
- **test(empresa)**: Simplificación de `EmpresaServiceTest` para verificar únicamente la delegación al caso de uso

## [2.3.0] - 2026-03-30

### 🚀 Migración Masiva a Arquitectura Hexagonal

#### Features
- **feat(clienteMovimiento)**: Nuevo endpoint `POST /byIds` para buscar movimientos de cliente por lista de IDs
- **feat(cliente)**: Nuevo endpoint `POST /byIds` para buscar clientes por lista de IDs
- **feat(hexagonal)**: Nuevos módulos hexagonales completos (puertos, casos de uso, JPA, controladores REST):
  - **`articulo`**: Migración completa del modelo `Articulo` de Kotlin a Java con arquitectura hexagonal. Casos de uso: creación, búsqueda (por ID, autoNumerico, máscara balanza, texto, voucher), cálculo de totales y actualización. Nuevo controlador REST con endpoints CRUD y DTOs (`ArticuloRequest`, `ArticuloResponse`, `ArticuloResponseForInvoiceData`, `TotalArticuloResponse`)
  - **`articulomovimiento`**: Migración completa del modelo `ArticuloMovimiento` de Kotlin a Java con arquitectura hexagonal. Casos de uso: creación, consulta por ID, por clienteMovimientoId, por stockMovimientoId, autocompletado de totales y guardado masivo
  - **`comprobante`**: Migración completa del modelo `Comprobante` de Kotlin a Java con arquitectura hexagonal. Casos de uso: consulta por módulo, comprobantes asociables, comprobantes disponibles y búsqueda por ID
  - **`cuenta`**: Migración completa del modelo `Cuenta` de Kotlin a Java con arquitectura hexagonal. Casos de uso: listado completo, listado con cuenta maestra, búsqueda por cuenta maestra y por número de cuenta
  - **`transferencia`**: Migración completa del modelo `Transferencia` de Kotlin a Java con arquitectura hexagonal. Caso de uso: búsqueda por identificador único
- **feat(controllers)**: Nuevos controladores REST para los módulos hexagonales: `ArticuloController`, `ArticuloMovimientoController`, `ComprobanteController`, `CuentaController`, `TransferenciaController`
- **feat(facade)**: Actualización de servicios facade (`PrecioController`, `StockController`, `TransferenciasController`, `VouchersController`) para usar los nuevos servicios hexagonales
- **feat(clients)**: Actualización de `ArticuloClient`, `CuentaClient`, `ParametroClient` con nuevos builders compatibles con Spring Boot 4.0.6

### Changed
- **refactor(dto)**: Ampliación de `ValorMovimientoDto` con los campos `movClieId`, `clienteId`, `nroPlanCta`, `created` y `updated`
- **refactor(mapper)**: Actualización de `ValorMovimientoDtoMapper` para incluir los nuevos campos con null-guard en `valor.getConcepto()`
- **refactor(clients)**: Mejora en `ArticuloBarraClientBuilder`, `ArticuloClientBuilder`, `CuentaClientBuilder`, `ParametroClientBuilder` añadiendo `JacksonDecoder` con `KotlinModule` para mejor compatibilidad con modelos Kotlin
- **refactor(clients)**: Adición de `SpringMvcContract` y `JacksonEncoder` en `ArticuloClientBuilder` para soportar solicitudes y respuestas JSON
- **refactor(controller)**: Mejora en `ArticuloController.add()` especificando `consumes` y `produces` como "application/json"
- **refactor(model)**: Adición de `@JsonIgnoreProperties(ignoreUnknown = true)` en modelos Kotlin para ignorar propiedades desconocidas en JSON
- **refactor(model)**: Nuevo campo `porcentajeDescuentoPersonal` en `ParametroDto.kt`
- **refactor(service)**: Mejora en logging de `ArticulosService` con mensajes más descriptivos
- **refactor(service)**: Uso de `Jsonifier` en lugar de serialización manual en `ArticulosService`

### Removed
- **chore(cleanup)**: Eliminación de modelos Kotlin obsoletos: `Articulo.kt`, `Comprobante.kt`, `Cuenta.kt`, `Transferencia.kt` y sus DTOs asociados
- **chore(cleanup)**: Eliminación de repositorios Kotlin obsoletos: `ArticuloRepository.kt`, `ArticuloMovimientoRepository.kt`, `ComprobanteRepository.kt`, `CuentaRepository.kt`, `TransferenciaRepository.kt`
- **chore(cleanup)**: Eliminación de controladores y servicios Java antiguos reemplazados por módulos hexagonales

### Dependencies
- **chore(deps)**: Actualización de Spring Boot 4.0.4→4.0.6 en `pom.xml`
- **chore(deps)**: Actualización de Kotlin 2.3.20→2.3.21 en `pom.xml`
- **chore(deps)**: Actualización de SpringDoc OpenAPI 3.0.2→3.0.3 en `pom.xml`
- **chore(deps)**: Actualización de OpenPDF 3.0.0→3.0.3 en `pom.xml`
- **chore(deps)**: Actualización de MySQL Connector 9.5.0→9.6.0 en `pom.xml`
- **chore(deps)**: Nueva dependencia `feign-jackson` para soporte de serialización JSON en clientes Feign
- **chore(deps)**: Nueva dependencia `commons-fileupload` 1.6.0 en dependencyManagement

## [2.2.0] - 2026-03-23

### Changed
- **refactor(migracion)**: Migración completa del modelo `ArticuloMovimiento` de Kotlin a Java para mejor compatibilidad con Spring Boot 4 y consistencia con el resto de la codebase
- **refactor(clients)**: Simplificación masiva de los builders de clientes Feign (`ArticuloBarraClientBuilder`, `ArticuloClientBuilder`, `CuentaClientBuilder`, `ParametroClientBuilder`) eliminando la configuración compleja de SpringEncoder/SpringDecoder que ya no es necesaria con Spring Cloud 2025
- **refactor(excepciones)**: Migración de `StockMovimientoException` de Kotlin a Java, manteniendo ambas variantes para compatibilidad hacia atrás
- **refactor(servicios)**: Adición de `@RequiredArgsConstructor` y eliminación de constructores manuales en `ArticuloMovimientoService`, `StockMovimientoService`
- **refactor(impresion-fiscal)**: Extensión del endpoint de impresión fiscal con nuevo parámetro `stockMovimientoId` para obtener datos de stock y artículos asociados
- **chore(deps)**: Actualización de Kotlin de 2.3.0 a 2.3.20 en `pom.xml`

### Features
- **feat(impresion-fiscal)**: Nuevo endpoint `/fiscal/{ipAddress}/{hWnd}/{clienteId}/{comprobanteId}/{comprobanteOrigenId}/stock/{stockMovimientoId}` que incluye datos de stock y artículos asociados
- **feat(repositories)**: Nuevos métodos de consulta `findAllByStockMovimientoId` en `ArticuloMovimientoRepository` y `findByStockMovimientoId` en `StockMovimientoRepository`

## [2.1.1] - 2026-02-27

### Changed
- **refactor(facturacion-exportacion)**: Cambio del tipo de dato `cantidad` de `Integer` a `BigDecimal` en `FacturaExportacionFacturadorItem` para permitir cantidades decimales en facturación de exportación
- **refactor(facturacion-exportacion)**: Ajuste en `FacturaElectronicaExportacionService` para usar `BigDecimal` directamente sin conversión a `int`
- **refactor(service)**: Simplificación de inyección de dependencias en `ArticuloMovimientoTemporalService` usando `@RequiredArgsConstructor`

## [2.1.0] - 2026-01-31

### Features
- **feat(invoicedata)**: Nuevo módulo hexagonal `InvoiceData` para consulta de datos de facturación completa
  - Nuevo endpoint GET `/api/core/invoiceData/{clienteMovimientoId}` para obtener datos completos de factura
  - Modelo de dominio `InvoiceData` con información de cliente, movimiento, CAE y comprobante asociado
  - Implementación completa de arquitectura hexagonal:
    - `domain/`: Modelos y puertos de entrada
    - `application/`: Servicios y casos de uso
    - `infrastructure/`: DTOs, mappers y controlador REST
  - Mappers para transformación de entidades: `InvoiceDataMapper`, `ClienteMovimientoMapper`, `RegistroCaeMapper`, `ArticuloMovimientoMapper`, `ClienteMapper`, `EmpresaMapper`, `ComprobanteMapper`, `ComprobanteAfipMapper`, `MonedaMapper`, `ConceptoFacturadoMapper`
  - DTOs de respuesta estructurados: `InvoiceDataResponse`, `ClienteMovimientoResponse`, `RegistroCaeResponse`, etc.

### Changed
- **refactor(stock)**: Simplificación de inyección de dependencias en `StockService` usando `@RequiredArgsConstructor` en lugar de constructor manual
- **refactor(empresa)**: Adición de método `toResponse()` en `EmpresaMapper` para soporte de DTOs en módulo invoicedata
- **refactor(model)**: Actualización de `ClienteMovimiento` y `RegistroCae` con nuevas relaciones JPA para soporte de consultas enriquecidas

### Dependencies
- **chore(deps)**: Actualización de Spring Boot 4.0.1 → 4.0.2

## [2.0.0] - 2026-01-27

### 🚀 Major Release - Migración de Legajo a Arquitectura Hexagonal y Actualización de Dependencias

#### Breaking Changes
- **refactor!**: Migración completa del módulo Legajo a arquitectura hexagonal con reorganización de paquetes y estructura de código
- **refactor!**: Actualización de dependencias principales: Spring Boot 3.5.8→4.0.1, Spring Cloud 2025.0.0→2025.1.0
- **refactor!**: Eliminación de tipos nullable en repositorios Kotlin para mayor consistencia de tipos
- **refactor!**: Cambio en modelo Reserva de `Date` a `LocalTime` para `horaVencimiento`
- **refactor!**: Actualización de builders de clientes Feign para compatibilidad con Spring Boot 4

#### Features
- **feat**: Nueva implementación hexagonal del módulo Legajo con controladores, servicios, dominio y persistencia
- **feat**: Nuevos endpoints para Legajo: GET `/legajo/`, GET `/legajo/{legajoId}`, POST `/legajo/search`, POST `/legajo/`
- **feat**: Servicio de búsqueda avanzada de legajos por substrings
- **feat**: Actualización de dependencias: ZXing 3.5.3→3.5.4, MySQL Connector 9.4.0→9.5.0, SpringDoc OpenAPI 2.8.10→3.0.1, ModelMapper 3.2.4→3.2.6, Commons Lang 3.18.0→3.20.0

#### Changed
- **refactor**: Reorganización completa del módulo Legajo a estructura hexagonal:
  - `hexagonal/legajo/domain/` - Modelos de dominio y puertos
  - `hexagonal/legajo/application/` - Casos de uso y servicios
  - `hexagonal/legajo/infrastructure/` - Adaptadores web y persistencia

## [1.3.0] - 2026-01-08

### Features
- feat: Nuevo endpoint GET `/proveedorMovimiento/resumen/iva/compras/posicion/{anho}/{mes}` para obtener resumen mensual de IVA de compras por posición, basado en cambios en `ProveedorMovimientoService.java`, adición de `ResumenIvaComprasMensualPosicion.java`, `ResumenIvaComprasMensualPosicionResponse.java` y mappers asociados

## [1.2.0] - 2026-01-07

### Features
- feat: Mejora en el endpoint GET `/proveedorMovimiento/resumen/iva/compras/{anho}/{mes}` para incluir el nombre del negocio en la respuesta, basado en cambios en `ProveedorMovimientoController.java`, adición de `ResumenIvaComprasMensualResponse.java` y `ResumenIvaComprasMensualDtoMapper.java`

## [1.1.0] - 2026-01-06

### Features
- feat: Nuevo endpoint GET `/proveedorMovimiento/resumen/iva/compras/{anho}/{mes}` para obtener resumen mensual de IVA de compras
- feat: Nuevo modelo de dominio `ResumenIvaComprasMensual` para cálculos de IVA
- feat: Mejora en la lógica de cálculo de neto en `RegistraFacturaService` incluyendo neto105

### Changed
- refactor: Migración completa de `ProveedorMovimiento` a arquitectura hexagonal con dominio y puertos
- refactor: Actualización de imports en controladores para usar modelos de dominio en lugar de entidades
- refactor: Refactorización de `NegocioController` con parámetro renombrado `negocioIdExcept`
- refactor: Eliminación de anotación `@Transient` innecesaria en `NegocioEntity`
- refactor: Agregado `@RequiredArgsConstructor` en `TransaccionFacturaProgramaDiaService`

### Fixed
- fix: Ajuste en cálculo de neto para incluir neto105 en facturación

## [1.0.0] - 2026-01-01

### 🚀 Major Release - Migración a Arquitectura Hexagonal

#### Breaking Changes
- **refactor!**: Migración completa a arquitectura hexagonal con reorganización de paquetes y estructura de código
- **refactor!**: Actualización de dependencias principales: Java 24→25, Kotlin 2.2.21→2.3.0, Spring Boot 3.5.6→3.5.8
- **refactor!**: Reorganización de módulos de facturación en estructura hexagonal nacional/exportación

#### Features
- **feat**: Nueva funcionalidad de facturación de exportación con `FacturaExportacionResponse` y `FacturaExportacionFacturadorItem`
- **feat**: Sistema de ajuste automático de netos e ivas para comprobantes de compras
- **feat**: Implementación de relación proveedor-movimiento con negocio
- **feat**: Optimización del pool de conexiones de base de datos (10→20 conexiones)
- **feat**: Migración completa del módulo Empresa a arquitectura hexagonal
- **feat**: Migración completa del módulo ProveedorMovimiento a arquitectura hexagonal
- **feat**: Migración completa del módulo de facturación nacional a arquitectura hexagonal

#### Changed
- **refactor**: Reorganización completa de servicios a arquitectura hexagonal:
  - `hexagonal/empresa/` - Gestión de empresas
  - `hexagonal/facturacion/arca/nacional/` - Facturación nacional
  - `hexagonal/facturacion/arca/exportacion/` - Facturación de exportación
- **refactor**: Actualización de imports y referencias en toda la codebase
- **refactor**: Mejora en nomenclatura de campos (hwnd→hWnd, impositivoId→idImpositivo)
- **refactor**: Actualización de Dockerfile y CI/CD pipeline para Java 25
- **refactor**: Mejora en manejo de transacciones con `@Transactional` en servicios críticos

#### Fixed
- **fix**: Corrección de nombres de atributos en entidades Kotlin para mayor consistencia
- **fix**: Optimización de consultas en `ArticuloMovimientoTemporalRepository` con nombres de métodos corregidos
- **fix**: Mejora en manejo de errores y logging en servicios de facturación

#### Performance
- **perf**: Incremento del pool de conexiones Hikari de 10 a 20 para mejor throughput
- **perf**: Optimización de métodos de búsqueda con nombres de campos corregidos

#### Documentation
- **docs**: Actualización de diagramas de arquitectura para reflejar la nueva estructura hexagonal
- **docs**: Documentación de nuevos endpoints de facturación de exportación
- **docs**: Actualización de badges y versiones en README.md

## [0.13.3] - 2025-11-04

### Fixed
- fix: Corrección del orden de sorting en `ProveedorMovimientoService.findAllByRegimenInformacionCompras` para usar `prefijo` en lugar de `puntoVenta`

## [0.13.2] - 2025-11-04

### Features
- feat: Nuevo endpoint GET `/proveedorMovimiento/arca/regimen/informacion/compras/{desde}/{hasta}` para obtener movimientos de régimen de información de compras
- feat: Nuevo método `findAllByRegimenInformacionCompras` en `ProveedorMovimientoService` con filtrado por IVA
- feat: Nuevo método `findAllByFechaContableBetweenAndComprobanteLibroIva` en `ProveedorMovimientoRepository`

### Changed
- refactor: Refactorización de `ProveedorMovimientoController` con `@RequiredArgsConstructor` y eliminación de constructor manual
- refactor: Refactorización de `ProveedorMovimientoService` con `@RequiredArgsConstructor` y eliminación de constructor manual

## [0.13.1] - 2025-11-02

### Features
- feat: Nuevo endpoint GET `/clienteMovimiento/arca/regimen/informacion/ventas/{desde}/{hasta}` para obtener movimientos de régimen de información de ventas
- feat: Nuevo método `findAllByRegimenInformacionVentas` en `ClienteMovimientoService` con filtrado por IVA
- feat: Nuevo método `findAllByFechaComprobanteBetweenAndComprobanteLibroIva` en `ClienteMovimientoRepository`

### Changed
- refactor: Refactorización de `ClienteMovimientoController` con `@RequiredArgsConstructor` y eliminación de constructor manual

## [0.13.0] - 2025-10-31

### Features
- feat: Nuevo endpoint POST `/clienteMovimiento/` para crear movimientos de cliente
- feat: Nuevos endpoints en LegajoController: `findByLegajoId` y `findAllBySearch`
- feat: Nuevo servicio `LegajoSearchService` para búsqueda de legajos
- feat: Nueva excepción `LegajoException` para manejo de errores de legajoEntity
- feat: Actualización de dependencias: Kotlin 2.2.20→2.2.21

### Changed
- refactor: Migración masiva de modelos de Kotlin a Java (ClienteMovimiento, CuentaMovimiento, RegistroCae)
- refactor: Refactorización completa de controladores con `@RequiredArgsConstructor` y eliminación de constructores manuales
- refactor: Mejora en manejo de excepciones con `ResponseStatusException` en controladores
- refactor: Actualización de builders a métodos estáticos en modelos Java
- refactor: Centralización de serialización JSON usando `Jsonifier` en lugar de `JsonMapper`
- refactor: Optimización de constructores en servicios facade (ArticulosService, ContabilidadService, etc.)

### Fixed
- fix: Corrección de códigos de métodos de pago para tarjetas prepagas en FacturacionService

## [0.11.0] - 2025-09-22

### Breaking Changes
- refactor!: Eliminación de requisitos de autenticación, ahora permite todas las solicitudes sin verificaciones de seguridad.

### Changed
- refactor: Centralización de la serialización JSON usando Jsonifier en los modelos Negocio y FacturacionDto.
- refactor: Agregado @RequiredArgsConstructor y eliminación del constructor manual en NegocioService.
- refactor: Mejora del logging en NegocioService y FacturacionElectronicaService.
- refactor: Simplificación de la creación de ResponseEntity en MakeFacturaController.

## [0.10.0] - 2025-09-21

### Features
- feat: Nuevos tipos de tarjeta de crédito agregados: "Visa Prepaga" y "Mastercard Prepaga"
- feat: Actualización de dependencias principales (Spring Boot 3.5.5→3.5.6, Kotlin 2.2.10→2.2.20, OpenPDF 2.4.0→3.0.0)

### Breaking Changes
- refactor!: Eliminación de configuración CORS centralizada (WebConfig.java)
- refactor!: Nueva configuración de seguridad con autenticación básica requerida
- refactor!: Eliminación de BasicAuthRequestInterceptor

### Changed
- refactor: Refactorización masiva de controladores para usar inyección de dependencias con Lombok
- refactor: Mejora en serialización JSON usando Jsonifier centralizado
- chore: Eliminación de configuración CORS de bootstrap.yml

### Docs
- docs: Actualización de versiones de dependencias en README.md

## [0.9.0] - 2025-09-07
### Features
- feat: Servicios de consulta `SaldoArticuloService`, `SaldoFechaService` y `ClienteSearchService` agregados.
- feat: Utilidad de serialización JSON centralizada (`Jsonifier`).
- feat: Nueva clase utilitaria `ToolService` para operaciones de fecha, texto y números.

### Changed
- refactor: Migración y reorganización de servicios y repositorios de `api.rest` a `core.service`.
- refactor: Limpieza y mejora de interfaces en repositorios y servicios.
- refactor: Mejora de logging y trazabilidad en servicios críticos.
- chore: Eliminación definitiva de archivos y configuraciones obsoletas (`logback-spring.xml`, `application.yml`, etc).

### Fixed
- fix: Ajustes menores en la configuración de CORS y Consul en `bootstrap.yml`.

### Docs
- docs: Actualización de diagramas y documentación para reflejar la nueva arquitectura y utilidades.
## [0.8.0] - 2025-08-31
### Features
- feat: Nuevo servicio `ProductsService` para procesamiento de productos y vouchers.
- feat: Nuevos endpoints REST para consulta de numeración de facturas y notas de crédito.
- feat: Servicios de consulta `SaldoArticuloService` y `SaldoFechaService` agregados.

### Changed
- chore: Actualización de dependencias principales:
    - spring-boot-starter-parent 3.5.5
    - kotlin 2.2.10
    - openpdf 2.4.0
    - springdoc-openapi-starter-webmvc-ui 2.8.10
- refactor: Mejora de lógica y trazabilidad en `ClienteMovimientoService` y `ComprobanteService`.
- refactor: Ajuste de endpoints y lógica de numeración de comprobantes.
- refactor: Limpieza y mejora de interfaces en repositorios y servicios.
- chore: Eliminación de `logback-spring.xml` y migración completa a configuración YAML.

### Fixed
- fix: Corrección de lógica en la obtención del próximo número de factura y nota de crédito.
- fix: Ajustes en la configuración de CORS y Consul en `bootstrap.yml`.

### Docs
- docs: Actualización de diagramas y documentación para reflejar la nueva arquitectura y dependencias.

## [0.7.2] - 2025-08-27
### Changed
- refactor: Mejora de lógica y trazabilidad en `ClienteMovimientoService` y `ComprobanteService`.
- refactor: Se agrega método `findAllAsociables()` y se ajusta la consulta de comprobantes asociables.
- refactor: Limpieza y mejora de interfaces en `ComprobanteRepository`.
## [0.7.1] - 2025-08-09
### Features
- feat: Nuevos servicios de consulta (`ClienteSearchService`, `SaldoArticuloService`, `SaldoFechaService`).
- feat: Centralización y mejora de utilidades en `ToolService`.
- feat: Refactor y migración de servicios y repositorios de `api.rest` a `core.service`.
- feat: Mejora de logging y trazabilidad en servicios críticos.

### Changed
- chore: Actualización de dependencias principales:
    - spring-boot-starter-parent 3.5.4
    - openpdf 2.2.4
    - mysql-connector-j 9.4.0
- refactor: Cambios en endpoints de controladores para mayor consistencia REST.
- refactor: Mejoras en la gestión de comprobantes y facturación.

### Removed
- chore: Eliminación de archivos y configuraciones obsoletas (`logback-spring.xml`, `application.yml`, scripts de Maven Wrapper, etc).

### Docs
- docs: Actualización de diagramas y documentación para reflejar la nueva arquitectura y dependencias.
## [0.7.0] - 2025-07-24
### Features
- feat: Integración de Spring Security y configuración de seguridad básica.
- feat: Migración de Eureka a Consul para service discovery (dependencias, configuración y código).
- feat: Incorporación de Jacoco para cobertura de tests.
- feat: Nuevos servicios de consulta (`ClienteSearchService`, `SaldoArticuloService`, `SaldoFechaService`).
- feat: Integración de SonarCloud en CI/CD.

### Changed
- refactor: Migración y reorganización de servicios y repositorios de `api.rest` a `core.service`.
- refactor: Centralización y mejora de utilidades en `ToolService`.
- refactor: Mejora de logging y trazabilidad en servicios críticos.
- refactor: Actualización de dependencias principales (`spring-boot-starter-actuator`, `spring-cloud-starter-consul-discovery`, `commons-lang3`).

### Removed
- chore: Eliminación de archivos y configuraciones obsoletas (`logback-spring.xml`, `application.yml`, `Dockerfile.local`, scripts de Maven Wrapper).
- chore: Eliminación de anotaciones y configuraciones de Eureka.

### Docs
- docs: Actualización de diagramas y documentación para reflejar la migración a Consul y la integración de seguridad.

## [0.6.1] - 2025-07-17
### Changed
- refactor: Centralización de la serialización JSON en entidades de dominio (`jsonify()` en modelos Kotlin).
- refactor: Simplificación y mejora del logging en servicios de facturación y contabilidad.
- chore: Eliminación de métodos privados de logging redundantes en servicios Java.
- chore: Ajuste de niveles de log y trazabilidad en operaciones críticas.

## [0.6.0] - 2025-07-15

### Features
- **Gestión de Cotizaciones:** Implementado sistema para la gestión de cotizaciones de monedas y conversión de valores.
- **Facturación y Transacciones:**
    - Añadido endpoint para el registro de transacciones.
    - Implementado módulo de "snapshots" para el estado de las transacciones.
    - Mejorado el control de comprobantes faltantes.
    - Añadido campo `diferenciaWeb` para control de montos en facturas web.
- **Artículos y Productos:**
    - Implementada la replicación de códigos de barras y la actualización de artículos.
    - Añadida lógica para priorizar tarifas de feriados en la determinación de SKUs.
- **Informes y Consultas:**
    - Añadido endpoint para obtener un resumen diario de asientos contables.
- **Gestión de Feriados:** Implementado CRUD para la entidad `Feriado`.

### Fixes
- **Cálculos y Precisión:** Corregidos problemas con el cálculo de decimales en la facturación.
- **Validaciones:** Añadida validación de nulos para productos infantiles.
- **Precios:** Corregido el cálculo del precio de artículos basado en la fecha.
- **Configuración:** Ajustada la configuración de CORS.

### Refactor
- **Facturación:** Refactorización profunda del sistema de facturación, incluyendo la migración de DTOs a Java y la reorganización de la lógica de negocio.
- **Cotizaciones:** Optimizado el manejo de cotizaciones para mejorar la precisión y el rendimiento.

## [0.5.1] - 2025-07-10

### Changed
- refactor(billing): migrar FacturacionDto a Java y mejorar lógica

### Docs
- docs(release): actualizar CHANGELOG y refactorizar DTO

## [0.5.0] - 2025-07-09

### Added
- feat(billing): add endpoint for transaction registration

### Changed
- refactor(controller): improve error handling in ClienteMovimiento
- refactor(billing): migrar FacturacionDto a Java y mejorar lógica

### Docs
- update CHANGELOG, README, and Dockerfile for new version

## [0.4.2] - 2025-07-08

### Docs
- Eliminar información de contacto personal del README.

## [0.4.1] - 2025-07-06

### Fixed
- fix(docs): restaurar pipeline y diagramas de documentación

## [0.4.0] - 2025-07-05

### Added
- feat(snapshot): Implementar módulo de snapshot y actualizar dependencias.
- feat(iva): Implementar módulo de posición IVA y mejorar sistema de facturación.
- feat(comprobantes): Optimizar control de comprobantes faltantes y actualizar dependencias.
- feat(consolidado): Implementar control de comprobantes faltantes.
- feat(transferencias): Implementar regeneración de transferencias entre negocios.
- feat(api): Implementar HATEOAS y gestión de rubros/artículos.
- feat(currency): Implementar sistema de gestión de tipos de cambio.
- feat(articulos): Implementar actualización de artículos.
- feat(billing): Añadir campo diferenciaWeb para control de montos en facturas web.
- feat(replication): Implementar sistema de replicación de códigos de barras.
- feat(ProductoSkuService): Priorizar día feriado en determinación de tarifa.
- Agregado de la posibilidad de replicación automática de un artículo a otros negocios.

### Fixed
- fix(cors): Ajustar configuración CORS para permitir acceso desde core-service.
- fix(api): Mejorar manejo de errores y logging en impresión fiscal.

### Changed
- refactor(comprobantes): Optimizar control de comprobantes faltantes y mapeo JPA.
- refactor(facturacion): Reorganizar proceso de facturación y actualizar dependencias.
- refactor(cotizaciones): Implementar sistema de conversión entre monedas.
- refactor(monedas): Optimizar manejo de cotizaciones y precisión decimal.
- docs: Actualización de documentación y mejoras en el servicio core.
- docs(readme): Actualizar documentación y enlaces de API.
- Eliminación de copia de actualización de precios.
- Dominio eterea.termaliasa.com agregado a los origenes permitidos de CORS.
- Configuracion de CORS.

## [0.3.0] - 2025-07-02
### Feat
- implementar HATEOAS y gestión de rubros/artículos
- implement exchange rate management system

## [0.7.1] - 2025-08-05
- chore: Actualización de dependencias principales:
  - spring-boot-starter-parent a 3.5.4
  - openpdf a 2.2.4
  - mysql-connector-j a 9.4.0
- fix: Correcciones menores y refactorizaciones internas (ver historial de commits)
### Fix
- ajustar configuración CORS para permitir acceso desde core-service
- mejorar manejo de errores y logging en impresión fiscal
- implementar sistema de conversión entre monedas
- optimizar manejo de cotizaciones y precisión decimal

### Docs
- actualización de documentación y mejoras en el servicio core - Actualización del README con nuevas badges y secciones - Creación del CHANGELOG siguiendo Keep a Changelog - Optimización del comando Maven en GitHub Actions - Mejora en el manejo de conceptos facturados - Actualización de versiones de dependencias - Closes #1
- actualizar documentación y enlaces de API
