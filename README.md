
---

## 👤 Microservicio de Gestión de Clientes

```markdown
# 👥 FoodExpress – Client Service

![Client Service](https://img.shields.io/badge/status-production-brightgreen)  
![Java](https://img.shields.io/badge/java-17-blue)  
![Spring Boot](https://img.shields.io/badge/spring--boot-3.4.6-green)  
![License](https://img.shields.io/badge/license-MIT-blue)

---

## 📝 Descripción

Microservicio responsable de:
- Registro, consulta, actualización y eliminación de clientes.
- Validación de RUT chileno, email y cifrado de contraseñas.
- Documentación automática de la API con OpenAPI/Swagger.

---

## 🚀 Características

- **RESTful Endpoints** para `CRUD` de clientes.
- **Validación avanzada** de RUT (`@Pattern`), email (`@Email`) y fechas.
- **Cifrado de contraseñas** con SHA-256 + salt.
- **Swagger UI** disponible en `/doc/swagger-ui.html`.
- **Spring Security** para proteger endpoints.
- **Logs** detallados con SLF4J y Spring Security debug.

---

## 📐 Arquitectura

![Arquitectura Clientes](docs/architecture-client.puml)  
> Utiliza el mismo API Gateway y sistema de mensajería compartido.

---

## 🛠️ Instalación

1. **Clonar el repositorio**  
   ```bash
   git clone https://github.com/OrionTheProgramer/client-service.git
   cd client-service
