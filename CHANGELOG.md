
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
- implementar módulo de snapshot y actualizar dependencias
- implementar módulo de posición IVA y mejorar sistema de facturación
- optimizar control de comprobantes faltantes y actualizar dependencias
- implementar control de comprobantes faltantes
- implementar regeneración de transferencias entre negocios
- implementar HATEOAS y gestión de rubros/artículos
- implement exchange rate management system
- implementar actualización de artículos
- add diferenciaWeb field for web-invoice amount control
- implement barcode replication system
- priorizar día feriado en determinación de tarifa

### Fix
- ajustar configuración CORS para permitir acceso desde core-service
- mejorar manejo de errores y logging en impresión fiscal

### Refactor
- optimizar control de comprobantes faltantes y mapeo JPA
- reorganizar proceso de facturación y actualizar dependencias
- implementar sistema de conversión entre monedas
- optimizar manejo de cotizaciones y precisión decimal

### Docs
- actualización de documentación y mejoras en el servicio core - Actualización del README con nuevas badges y secciones - Creación del CHANGELOG siguiendo Keep a Changelog - Optimización del comando Maven en GitHub Actions - Mejora en el manejo de conceptos facturados - Actualización de versiones de dependencias - Closes #1
- actualizar documentación y enlaces de API
