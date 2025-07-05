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