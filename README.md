# ETEREA.core.api.rest

[![ETEREA.core-service CI](https://github.com/ETEREA-services/ETEREA.core-service/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/ETEREA-services/ETEREA.core-service/actions/workflows/maven.yml)

## Descripción

Servicio Core para la gestión financiera y contable, implementado con una arquitectura mixta Java/Kotlin. Proporciona:

- Gestión de transferencias entre negocios
- Control de movimientos contables y valores
- Administración de cotizaciones de monedas
- Gestión de artículos y rubros con listas de precios

## Stack Tecnológico

### Backend
- Java 21
- Kotlin 2.1.10
- Spring Boot 3.4.3
- Spring Cloud 2024.0.0
  - Netflix Eureka Client
  - OpenFeign
- Spring Security
- Spring Data JPA
- Spring HATEOAS
- Spring WebFlux
- Spring Validation
- Log4j2

### Utilidades
- OpenPDF 2.0.3 (Generación de PDFs)
- ZXing 3.5.3 (Códigos de Barras y QR)
- Lombok
- ModelMapper 3.2.2
- Caffeine Cache 3.2.0
- MySQL Connector 9.2.0

### Documentación
- SpringDoc OpenAPI UI 2.8.5

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

## Configuración del Proyecto

### Requisitos
- JDK 21
- Maven 3.8+
- MySQL 9.2+

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

## Documentación API

La documentación de la API está disponible en:

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- OpenAPI YAML: http://localhost:8080/v3/api-docs.yaml

## Documentación Adicional

- [Documentación del Proyecto](https://eterea-services.github.io/ETEREA.core-service/)
- [Wiki del Proyecto](https://github.com/ETEREA-services/ETEREA.core-service/wiki)

## Contribución

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un Pull Request

## Estado del Proyecto

El proyecto está en desarrollo activo. Ver [GitHub Projects](https://github.com/ETEREA-services/ETEREA.core-service/projects) para el roadmap.

## Características

- Proyecto mixto Java/Kotlin
- Documentación API con OpenAPI 3.0
- Soporte para transacciones distribuidas
- Integración con Eureka Service Discovery

## Notas Importantes

- El proyecto usa una combinación de Java y Kotlin
- Las entidades JPA están definidas en Kotlin
- Los servicios y controladores están en Java
- Se requiere configuración de Eureka para el registro de servicios
- La documentación de la API se genera automáticamente en tiempo de ejecución
