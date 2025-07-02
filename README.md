# ETEREA.core.api.rest

[![ETEREA.core-service CI](https://github.com/ETEREA-services/ETEREA.core-service/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/ETEREA-services/ETEREA.core-service/actions/workflows/maven.yml)
[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.21-blueviolet.svg)](https://kotlinlang.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-green.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.0.0-green.svg)](https://spring.io/projects/spring-cloud)
[![OpenAPI](https://img.shields.io/badge/OpenAPI-2.8.9-blue.svg)](https://springdoc.org/)
[![MySQL](https://img.shields.io/badge/MySQL-9.3.0-orange.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-Proprietary-red.svg)](LICENSE)
[![Version](https://img.shields.io/badge/Version-0.3.0-blue.svg)](https://github.com/ETEREA-services/ETEREA.core-service/releases)

## Descripción

Servicio Core para la gestión financiera y contable, implementado con una arquitectura mixta Java/Kotlin. Proporciona:

- Gestión de transferencias entre negocios
- Control de movimientos contables y valores
- Administración de cotizaciones de monedas
- Gestión de artículos y rubros con listas de precios
- Control de comprobantes faltantes
- Facturación electrónica
- Gestión de Posiciones IVA
- **Módulo de Snapshots para el registro de transacciones**
- **Implementación de HATEOAS**
- **Sistema de replicación de códigos de barras**

## Stack Tecnológico

### Backend
- Java 21
- Kotlin 2.1.21
- Spring Boot 3.5.3
- Spring Cloud 2025.0.0
  - Netflix Eureka Client
  - OpenFeign
- Spring Security
- Spring Data JPA
- Spring HATEOAS
- Spring WebFlux
- Spring Validation
- Log4j2

### Utilidades
- OpenPDF 2.2.3 (Generación de PDFs)
- ZXing 3.5.3 (Códigos de Barras y QR)
- Lombok
- ModelMapper 3.2.4
- Caffeine Cache
- MySQL Connector 9.3.0

### Documentación
- SpringDoc OpenAPI UI 2.8.9

## Arquitectura

El proyecto utiliza una arquitectura mixta:
- **Modelos y Repositorios**: Implementados en Kotlin para aprovechar sus características de null-safety y data classes
- **Servicios y Controladores**: Implementados en Java para mantener compatibilidad con librerías legacy

## Módulos Principales

- **Transferencias**: Gestión de transferencias entre negocios
- **Movimientos**: Control de movimientos contables y valores
- **Cotizaciones**: Administración de cotizaciones de monedas
- **Artículos**: Gestión de artículos y sus listas de precios
- **Rubros**: Categorización y gestión de rubros comerciales
- **Facturación**: Control de comprobantes y facturación electrónica
- **Posición IVA**: Gestión de las posiciones de IVA de clientes
- **Snapshot**: Registro de estados de transacciones.

## Configuración del Proyecto

### Requisitos
- JDK 21
- Maven 3.8+
- MySQL 9.3+

### Instalación
```bash
git clone https://github.com/ETEREA-services/ETEREA.core-service.git
cd ETEREA.core-service
mvn clean install
```

### Ejecución
```bash
mvn spring-boot:run
```

### Docker
```bash
# Construir imagen
docker build -t eterea/core-service .

# Ejecutar contenedor
docker run -p 8080:8080 eterea/core-service
```

## Documentación API

La documentación de la API está disponible en:

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- OpenAPI YAML: http://localhost:8080/v3/api-docs.yaml

## Documentación Adicional

- [Documentación del Proyecto](https://eterea-services.github.io/ETEREA.core-service/)
- [Wiki del Proyecto](https://github.com/ETEREA-services/ETEREA.core-service/wiki)
- [CHANGELOG](CHANGELOG.md)

## Contribución

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am '''Agrega nueva funcionalidad'''`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un Pull Request

## Estado del Proyecto

El proyecto está en desarrollo activo. Ver [GitHub Projects](https://github.com/ETEREA-services/ETEREA.core-service/projects) para el roadmap.

## Licencia

Este proyecto es privado y de uso exclusivo de Termalia S.A.

## Características

- Proyecto mixto Java/Kotlin
- Documentación API con OpenAPI 3.0
- Soporte para transacciones distribuidas
- Integración con Eureka Service Discovery
- Gestión de Posiciones IVA
- **Módulo de Snapshots**
- **Implementación de HATEOAS**
- **Sistema de replicación de códigos de barras**

## Notas Importantes

- El proyecto usa una combinación de Java y Kotlin
- Las entidades JPA están definidas en Kotlin
- Los servicios y controladores están en Java
- Se requiere configuración de Eureka para el registro de servicios
- La documentación de la API se genera automáticamente en tiempo de ejecución