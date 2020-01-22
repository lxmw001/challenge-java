![Logo](avalith_logo.png)

---
## Instrucciones
Realizar un fork de este repositorio, completar los datos de información de aplicante y cumplir con los objetivos mencionados más abajo.

## Información del Aplicante
* Nombre: 
* E-Mail: 
* Fecha/Hora de comienzo: 
* Fecha/Hora de finalización: 

## Objetivo del Challenge
### Realizar una aplicación con Spring Boot que exponga una API con los siguientes requerimientos funcionales utilizando la [API pública de SpaceX](https://documenter.getpostman.com/view/2025350/RWaEzAiG)

### Requerimientos Obligatorios:
1. La aplicación debe permitir listar los próximos lanzamientos mostrando los siguientes campos:
    - Nombre de la misión
    - Fecha de la misión
    - Nombre del cohete
    - Lugar de lanzamiento
2. La aplicación debe permitir guardar lanzamientos como favoritos asignándoles un _Tag_ o _Etiqueta_ y persistir los datos en una base de datos a elección. (Un lanzamiento puede tener muchas etiquetas y una etiqueta puede corresponder a varios lanzamientos favoritos).
3. La aplicación debe permitir mostrar los lanzamientos marcados como favoritos.
4. La Aplicación debe permitir modificar y/o eliminar los tags o etiquetas de los lanzamientos favoritos.
5. La Aplicación debe permitir eliminar lanzamientos favoritos.
6. La API de la Aplicación debe estar documentada en cuanto a:
    - Endpoints
    - Payloads
    - Parameters
    - Posibles Respuestas (Opcional)

### Requerimientos Opcionales:
1. Javadoc de los métodos utilizados para las funcionalidades requeridas.
2. Autenticación de usuarios (JWT o Cookie).
3. Utilización de ORM (Hibernate) u otra implementación de JPA.

#### Información Útil:
* [Documentación con Postman](https://learning.getpostman.com/docs/postman/api-documentation/documenting-your-api/)
* [Uso de RestTemplate](https://www.baeldung.com/rest-template)
* [Estándares de APIs Restful](https://hackernoon.com/restful-api-designing-guidelines-the-best-practices-60e1d954e7c9)
* [Spring Security con JWT](https://www.baeldung.com/spring-security-oauth-jwt)