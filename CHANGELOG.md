# Changelog

Todos los cambios notables en este proyecto serán documentados en este archivo.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
y este proyecto adhiere a [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased] - 2025-05-01

### Added
- Agregada relación `comprobanteAfip` en la entidad `Comprobante`
- Agregado logging en `ConsolidadoController`

### Changed
- Optimizado el proceso de búsqueda de comprobantes faltantes usando Set para búsquedas O(1)
- Refactorizado el código de `ConsolidadoService` para mejor legibilidad y mantenibilidad
- Actualizada la versión de Spring Boot a 3.4.5
- Actualizada la versión de MySQL Connector a 9.3.0
- Actualizada la versión de ModelMapper a 3.2.3

### Removed
- Eliminados métodos de logging redundantes en `ConsolidadoService`
- Eliminada lógica antigua de agrupación por letra y punto de venta

## [0.0.2] - 2025-03-24

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

## [0.0.1] - 2024-02-15

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

## [0.0.0] - 2023-12-30

### Added
- Migración a Java 21
- Implementación de sistema de cotizaciones
- Mejoras en el manejo de asientos contables
- Integración con sistema de facturación electrónica

### Changed
- Actualización de versión de Kotlin
- Actualización del conector MySQL
- Optimización del cálculo de totales de contabilidad

## [0.0.0-alpha] - 2023-05-27

### Added
- Migración inicial a Kotlin
- Implementación de entidades base
- Configuración inicial de Spring Boot

### Changed
- Actualización de dependencias principales
- Mejoras en la estructura del proyecto

## [0.0.0-pre] - 2022-08-10

### Added
- Implementación inicial del servicio
- Configuración básica de Spring Boot
- Entidades base del sistema
- Integración con base de datos

### Changed
- Configuración inicial del proyecto
- Estructura base del código 