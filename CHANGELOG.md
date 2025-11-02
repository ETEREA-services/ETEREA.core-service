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
- feat: Nueva excepción `LegajoException` para manejo de errores de legajo
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
