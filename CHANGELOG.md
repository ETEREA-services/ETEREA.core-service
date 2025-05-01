# Changelog

Todos los cambios notables en este proyecto serán documentados en este archivo.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
y este proyecto adhiere a [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Implementación del control de comprobantes faltantes
- Nuevo módulo de facturación electrónica
- Endpoint para búsqueda de facturas por rango
- Mejoras en el logging de grupos de comprobantes

### Changed
- Actualización de Spring Boot a 3.4.4
- Actualización de Spring Cloud a 2024.0.1
- Actualización de SpringDoc OpenAPI a 2.8.6
- Optimización de la configuración CORS
- Mejora en el manejo de relaciones JPA en ComprobanteFaltante

### Fixed
- Corrección en la búsqueda de comprobantes por punto de venta
- Mejora en el manejo de nulos en el servicio de consolidado
- Optimización de consultas JPA

### Security
- Actualización de dependencias de seguridad

## [0.0.1] - 2024-03-15

### Added
- Implementación inicial del servicio core
- Módulos de transferencias, movimientos y cotizaciones
- Integración con Eureka y OpenFeign
- Documentación OpenAPI
- Configuración de Docker

### Changed
- Optimización de la arquitectura mixta Java/Kotlin
- Mejoras en el manejo de transacciones
- Actualización de dependencias

### Fixed
- Correcciones en el manejo de fechas
- Mejoras en la validación de datos
- Optimización de consultas a base de datos 