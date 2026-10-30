# Ejecutar proyecto
  Desde la carpeta raíz del proyecto se debe ejecutar el comando:

```
./gradlew bootRun
```

>Nota: Para esta demo se requiere la versión 21 de Java

# Configuración validación password
 En el archivo [application.properties](src%2Fmain%2Fresources%2Fapplication.properties)
 se encuentra la propiedad **config.formato-password.regex** para establecer la expresión regular requerida para validar
 el formato del campo password.
 
# Documentación OpenAPI

La documentación de los endpoints se encuentra disponible en:

[http://localhost:8080/endpoints](http://localhost:8080/endpoints)

# Ejemplos llamadas
Se presentaran llamadas de ejemplo con curl que pueden ser importadas en Postman (File -> Import o Ctrl+O).

## POST v1/users
La primera llamada debiera ser para la creación del usuario:
```
curl --location 'http://localhost:8080/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}'
```

## POST v1/auth/login
Luego se debiera realizar el login para acceder a los restantes endpoints.
```
curl --location 'http://localhost:8080/v1/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "juan@rodriguez.org",
    "password": "hunter2"
}'
```

La respuesta de este servicio devuelve el token JWT requerido.
Ej:
```
{
    "email": "juan@rodriguez.org",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MjAwNjY5MDcsImV4cCI6MTcyMDIzOTcwN30.4qbszRM7SsqxiSEgyCF_gb0FAZyqg5ixLmis-mhvXKYNMLhSnHldwf1LFb20Im5dpm4sg93pxR2LzOOn2YFBLg"
}
```

## GET v1/users
Listado de usuarios. Requiere token generado en login en header **Authorization**.
```
curl --location 'http://localhost:8080/v1/users' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MjAwNjY1ODgsImV4cCI6MTcyMDIzOTM4OH0.X_qgAUrC7EpOvHtzknNtHDOkhApIbCf49spRZP0bXr73y1bgI_BoPNufU3LSObuU0L-tb5NtwuuokTytDlX_WA'
```

## GET v1/users/\<UUID de usuario>
Obtención de usuario por su identificador. Requiere token generado en login en header **Authorization**.

```
curl --location 'http://localhost:8080/v1/users/8ab71cd7-473b-4fff-8d04-86b74bf77a24' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MjAwNjUwNTMsImV4cCI6MTcyMDIzNzg1M30.a923UT_weObEuhYc-hu2NZa1h4uINmrorW7B-pwUmnDHPl0Y2eYgTOgw09cTdAkZ0eCvuK85UWmRz5BGsSwicA'
```
>Nota: Identificador se puede recuperar de atributo userId devuelto en listado de usuarios.

## PUT v1/users/\<UUID de usuario>
Actualización de usuario. Requiere token generado en login en header **Authorization**.
```
curl --location --request PUT 'http://localhost:8080/v1/users/8ab71cd7-473b-4fff-8d04-86b74bf77a24' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MTk5ODI1NTIsImV4cCI6MTcyMDE1NTM1Mn0.DPL5TIb-84hiN17nkha7QgzCjajg-OJb_vl0qFgRQTKLcGmeGZqMlZwHriP2396LyiHw6N3XAWvt4SH8pT1NqQ' \
--data-raw '{
    "name": "Juan Rodriguez updated",
    "email": "juan@rodriguez.org",
    "password": "hunter2updated",
    "isactive": true,
    "phones": [
        {
            "number": "1234567",
            "citycode": "2",
            "countrycode": "56"
        }
    ]
}'
```

# Diagrama de la solución

```mermaid
sequenceDiagram
 actor Usr as Cliente Web
 participant App as Aplicación
 
 Usr ->>+App: POST v1/users
 Note right of Usr: Crear Usuario
 App -->>-Usr: Http 201
 Note right of Usr: Usuario Creado
 
 Usr ->>+App: POST v1/auth/login
 Note right of Usr: Envio email/password
 App -->>-Usr: Http 200
 Note right of Usr: Devuelve Token

 Usr ->>+App: GET v1/users
 Note right of Usr: Lista usuarios (Token requerido) 
 App -->>-Usr: Http 200
 Note right of Usr: Listado usuarios

 Usr ->>+App: GET v1/users/<UUID>
 Note right of Usr: Recupera usuario (token requerido)
 App -->>-Usr: Http 200
 Note right of Usr: Usuario asociado a UUID

 Usr ->>+App: PUT v1/users/<UUID>
 Note right of Usr: Actualiza usuario (token requerido)
 App -->>-Usr: Http 200
 Note right of Usr: Usuario actualizado

```
>Nota: Diagrama creado con [Mermaid](https://mermaid.js.org/). Es soportado en Github.\
> Para visualización en local es necesario instalar un plugin en el IDE que se esté utilizando. 
