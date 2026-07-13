# ETEREA.core-service

[![ETEREA.core-service CI](https://github.com/ETEREA-services/ETEREA.core-service/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/ETEREA-services/ETEREA.core-service/actions/workflows/maven.yml)
[![Java](https://img.shields.io/badge/Java-25-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk25-archive-downloads.html)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.0-blueviolet.svg)](https://kotlinlang.org/)

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-green.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.2-green.svg)](https://spring.io/projects/spring-cloud)
[![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0.3-blue.svg)](https://springdoc.org/)
[![MySQL](https://img.shields.io/badge/MySQL-9.7.0-orange.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-Proprietary-red.svg)](LICENSE)
[![Version](https://img.shields.io/badge/Version-2.5.0-blue.svg)](https://github.com/ETEREA-services/ETEREA.core-service/releases)

## Descripción

Servicio Core para la gestión financiera y contable, implementado con **arquitectura hexagonal**. Proporciona:

- **Arquitectura Hexagonal**: Implementación completa con puertos, adaptadores y casos de uso
- **Facturación Nacional y de Exportación**: Soporte completo para facturación electrónica argentina
- **Gestión de Artículos, Comprobantes, Cuentas, Transferencias**: Módulos hexagonales migrados a Java
- **InvoiceData**: Consulta de datos completos de facturación
- **Gestión de Legajos y Proveedores**: Con arquitectura hexagonal
- **Ajuste automático** de netos e ivas en comprobantes
- **Utilidades centralizadas**: serialización JSON, operaciones de fecha y texto

## Stack Tecnológico

- **Java 25** y **Kotlin 2.4.0**
- **Spring Boot 4.1.0** con Spring Cloud 2025.1.2
- **Arquitectura Hexagonal** para modularidad y testabilidad
- **Consul Discovery** y **OpenFeign**
  - Consul Discovery
  - OpenFeign
- Spring Data JPA
- Spring HATEOAS
- Spring WebFlux
- Spring Validation

### Utilidades
- OpenPDF 3.0.5 (Generación de PDFs)
- ZXing 3.5.4 (Códigos de Barras y QR)
- Lombok
- ModelMapper 3.2.6
- Caffeine Cache
- MySQL Connector 9.7.0
- Jacoco 0.8.13 (Cobertura de tests)
- Apache Commons Lang3 3.20.0

### Documentación
- SpringDoc OpenAPI UI 3.0.3

## Arquitectura

El proyecto utiliza una **arquitectura hexagonal** con implementación mixta:

### Módulos Hexagonales
- **`hexagonal/stock/articulo/`**: Gestión de artículos con puertos de entrada y salida (migrado a Java)
- **`hexagonal/stock/articulomovimiento/`**: Gestión de movimientos de artículos (migrado a Java)
- **`hexagonal/stock/stockmovimiento/`**: Gestión de movimientos de stock (migrado a Java)
- **`hexagonal/contable/cuenta/`**: Gestión de cuentas contables (migrado a Java)
- **`hexagonal/contable/cuentamovimiento/`**: Gestión de movimientos contables (migrado a Java)
- **`hexagonal/compras/proveedormovimiento/`**: Gestión de movimientos de proveedores
- **`hexagonal/comprobante/`**: Gestión de comprobantes (migrado a Java)
- **`hexagonal/transferencia/`**: Gestión de transferencias entre negocios (migrado a Java)
- **`hexagonal/empresa/`**: Gestión de empresas con puertos de entrada y salida
- **`hexagonal/negocio/`**: Gestión de negocios (sucursales) con puertos de entrada y salida
- **`hexagonal/legajo/`**: Gestión de legajos con arquitectura hexagonal
- **`hexagonal/invoicedata/`**: Consulta de datos completos de facturación con arquitectura hexagonal
- **`hexagonal/valormovimiento/`**: Gestión de movimientos de valores (migrado a Java)
- **`hexagonal/cierrecaja/anticipohaberes/`**: Gestión de anticipos de haberes en cierre de caja
- **`hexagonal/cierrecaja/processCierre/`**: Procesamiento de cierre de caja con conteos pendientes
- **`hexagonal/facturacion/arca/nacional/`**: Facturación electrónica nacional
- **`hexagonal/facturacion/arca/exportacion/`**: Facturación de exportación

### Estructura por Tecnología
- **Modelos de Dominio**: Implementados en Java siguiendo principios de Clean Architecture
- **Entidades JPA**: Implementadas en Java (migración progresiva desde Kotlin)
- **Servicios y Controladores**: Implementados en Java
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
- ✅ **Proyecto Java con migración progresiva desde Kotlin**
- ✅ **Clean Architecture** con separación clara de responsabilidades

### Funcionalidades Core
- ✅ **Facturación Nacional y Exportación** con integración AFIP
- ✅ **Ajuste Automático** de comprobantes
- ✅ **Gestión de Artículos** con arquitectura hexagonal (Java)
- ✅ **Gestión de Movimientos de Artículos** con arquitectura hexagonal (Java)
- ✅ **Gestión de Movimientos de Stock** con arquitectura hexagonal (Java)
- ✅ **Gestión de Comprobantes** con arquitectura hexagonal (Java)
- ✅ **Gestión de Cuentas Contables** con arquitectura hexagonal (Java)
- ✅ **Gestión de Movimientos Contables** con arquitectura hexagonal (Java)
- ✅ **Gestión de Movimientos de Valores** con arquitectura hexagonal (Java)
- ✅ **Gestión de Transferencias** con arquitectura hexagonal (Java)
- ✅ **Gestión de Empresas** con arquitectura hexagonal
- ✅ **Gestión de Negocios (sucursales)** con arquitectura hexagonal
- ✅ **Gestión de Legajos** con arquitectura hexagonal
- ✅ **Consulta de Datos de Facturación (InvoiceData)** con arquitectura hexagonal
- ✅ **Gestión de Proveedores** con arquitectura hexagonal
- ✅ **Control de Movimientos** contables y valores
- ✅ **Procesamiento de Cierre de Caja** con conteos pendientes
- ✅ **Gestión de Anticipos de Haberes** en cierre de caja

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
- Los modelos de dominio y entidades JPA están progresivamente migrados a Java
- Los casos de uso y controladores siguen principios de Clean Architecture
- Se requiere configuración de Consul para el registro de servicios
- La documentación de la API se genera automáticamente en tiempo de ejecución
- Todas las pruebas unitarias deben seguir la estructura hexagonal