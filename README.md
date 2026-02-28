# ETEREA.core.api.rest

[![ETEREA.core-service CI](https://github.com/ETEREA-services/ETEREA.core-service/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/ETEREA-services/ETEREA.core-service/actions/workflows/maven.yml)
[![Java](https://img.shields.io/badge/Java-25-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk25-archive-downloads.html)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.0-blueviolet.svg)](https://kotlinlang.org/)

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-green.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.0-green.svg)](https://spring.io/projects/spring-cloud)
[![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0.1-blue.svg)](https://springdoc.org/)
[![MySQL](https://img.shields.io/badge/MySQL-9.5.0-orange.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-Proprietary-red.svg)](LICENSE)
[![Version](https://img.shields.io/badge/Version-2.1.1-blue.svg)](https://github.com/ETEREA-services/ETEREA.core-service/releases)

## Descripción

Servicio Core para la gestión financiera y contable, implementado con una **arquitectura hexagonal mixta Java/Kotlin**. Proporciona:

- **Arquitectura Hexagonal**: Implementación completa con puertos, adaptadores y casos de uso
- **Facturación Nacional y de Exportación**: Soporte completo para facturación electrónica argentina
- Gestión de transferencias entre negocios
- Control de movimientos contables y valores
- **Servicios de consulta** de stock y clientes
- **Ajuste automático** de netos e ivas en comprobantes
- **Utilidades centralizadas**: serialización JSON, operaciones de fecha y texto

## Stack Tecnológico
- Spring Cloud 2025.1.0



## Stack Tecnológico

- **Java 25** y **Kotlin 2.3.0**
- **Spring Boot 4.0.2** con Spring Cloud 2025.1.0
- **Arquitectura Hexagonal** para modularidad y testabilidad
- **Consul Discovery** y **OpenFeign**
  - Consul Discovery
  - OpenFeign
- Spring Data JPA
- Spring HATEOAS
- Spring WebFlux
- Spring Validation
- Log4j2

### Utilidades
- OpenPDF 3.0.0 (Generación de PDFs)
- ZXing 3.5.4 (Códigos de Barras y QR)
- Lombok
- ModelMapper 3.2.6
- Caffeine Cache
- MySQL Connector 9.4.0
- Jacoco 0.8.13 (Cobertura de tests)
- Apache Commons Lang3 3.18.0

### Documentación
- SpringDoc OpenAPI UI 2.8.10

## Arquitectura

El proyecto utiliza una **arquitectura hexagonal** con implementación mixta:

### Módulos Hexagonales
- **`hexagonal/empresa/`**: Gestión de empresas con puertos de entrada y salida
- **`hexagonal/legajo/`**: Gestión de legajos con arquitectura hexagonal
- **`hexagonal/invoicedata/`**: Consulta de datos completos de facturación con arquitectura hexagonal
- **`hexagonal/facturacion/arca/nacional/`**: Facturación electrónica nacional
- **`hexagonal/facturacion/arca/exportacion/`**: Facturación de exportación

### Estructura por Tecnología
- **Modelos y Entidades**: Implementados en Kotlin para aprovechar null-safety y data classes
- **Servicios y Controladores**: Implementados en Java para compatibilidad con librerías legacy
- **Casos de Uso y Puertos**: Siguiendo principios de Clean Architecture

> **Nota:** Desde la versión 1.0.0, el proyecto implementa arquitectura hexagonal completa con migración de todos los módulos críticos.

## Módulos Principales

### Facturación
- **Facturación Nacional**: Sistema completo de facturación electrónica AFIP
- **Facturación de Exportación**: Nueva funcionalidad para comprobantes de exportación
- **Ajuste Automático**: Sistema de corrección automática de netos e ivas

### Gestión Empresarial
- **Transferencias**: Gestión de transferencias entre negocios
- **Movimientos**: Control de movimientos contables y valores
- **Cotizaciones**: Administración de cotizaciones de monedas

### Gestión de Productos
- **Artículos**: Gestión de artículos y sus listas de precios
- **Rubros**: Categorización y gestión de rubros comerciales
- **Inventario**: Control de stock y movimientos

### Consulta de Datos de Facturación
- **InvoiceData**: Endpoint para obtener datos completos de facturas por movimiento de cliente
- Integración con módulos de empresa, cliente, comprobante y CAE

### Servicios Transversales
- **Service Discovery**: Integración con Consul
- **Cobertura de tests**: Jacoco para análisis de cobertura
- **Utilidades**: Herramientas centralizadas para operaciones comunes

## Configuración del Proyecto

### Requisitos
- **JDK 25**
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

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **OpenAPI YAML**: http://localhost:8080/v3/api-docs.yaml

## Nuevas Funcionalidades v1.0.0

### 🚀 Facturación de Exportación
- Soporte completo para comprobantes de exportación
- Integración con AFIP para validación de datos
- Manejo de monedas extranjeras y cotizaciones

### 🔧 Ajuste Automático
- Corrección automática de netos e ivas en comprobantes de compras
- Validación de consistencia contable
- Logging detallado de ajustes realizados

### 🏗️ Arquitectura Hexagonal
- Migración completa a arquitectura hexagonal
- Separación clara de responsabilidades
- Mayor testabilidad y mantenibilidad

### ⚡ Mejoras de Rendimiento
- Pool de conexiones optimizado (20 conexiones)
- Consultas mejoradas con nombres de métodos corregidos
- Caché de Caffeine para operaciones frecuentes

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

El proyecto está en **desarrollo activo** con arquitectura hexagonal estable. Ver [GitHub Projects](https://github.com/ETEREA-services/ETEREA.core-service/projects) para el roadmap.

## Licencia

Este proyecto es privado y de uso exclusivo de Termalia S.A.

## Características

### Arquitectura
- ✅ **Arquitectura Hexagonal** completa con puertos y adaptadores
- ✅ **Proyecto mixto Java/Kotlin** optimizado
- ✅ **Clean Architecture** con separación clara de responsabilidades

### Funcionalidades Core
- ✅ **Facturación Nacional y Exportación** con integración AFIP
- ✅ **Ajuste Automático** de comprobantes
- ✅ **Gestión de Empresas** con arquitectura hexagonal
- ✅ **Control de Movimientos** contables y valores
- ✅ **Sistema de Transferencias** entre negocios

### Infraestructura
- ✅ **Documentación API** con OpenAPI 3.0
- ✅ **Service Discovery** con Consul
- ✅ **Cache distribuido** con Caffeine
- ✅ **Transacciones distribuidas** con JPA
- ✅ **Cobertura de tests** con Jacoco
- ✅ **CI/CD** con GitHub Actions

### Utilidades
- ✅ **Generación de PDFs** con OpenPDF
- ✅ **Códigos de barras y QR** con ZXing
- ✅ **Mapeo de objetos** con ModelMapper
- ✅ **Serialización JSON** centralizada
- ✅ **Herramientas de fecha y texto** utilidades

## Notas Importantes

- El proyecto utiliza arquitectura hexagonal desde la versión 1.0.0
- Las entidades JPA están definidas en Kotlin para mejor type safety
- Los casos de uso y controladores siguen principios de Clean Architecture
- Se requiere configuración de Consul para el registro de servicios
- La documentación de la API se genera automáticamente en tiempo de ejecución
- Todas las pruebas unitarias deben seguir la estructura hexagonal