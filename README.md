### keycloak-springboot3-application-part2

# Final Project - PART 2 - Billing and Security Management 🚀👾

## Microservices Deployment ⚙️

Environment Configuration:

    Docker Desktop: Ensure Docker Desktop is installed for efficient container management.
    
    Config-server: Initiate the centralized configuration service for consistent configuration management.
    
    Ms-discovery: Deploy the discovery service to maintain microservices availability and scalability.
    
    Ms-bills: Implement the billing microservice designed to efficiently manage customer invoices. Utilizing Spring Boot 3.1.2 and Java 17.
    
    Ms-gateway: Launch the API Gateway, acting as a Keycloak client.
    
    Ms-users: Another Keycloak client microservice for additional functionality.
  
## Security Configuration with Keycloak ⚠️

👉🏻 Users and Roles:

    - gisela (with the "USER" role) - Privileged access to the list of bills.
    - cosme (without a role) - No access to the visualization of all bills.
    - provider1 (no role but in the PROVIDERS group)
    - provider2 (no role but in the PROVIDERS group)

Additional Tasks:
Continuing from the partial practical task, we now focus on the billing system. In this stage, we're working on two new functionalities:

    - Different bills providers can create bills.
    - Users can search for their bills.

👉🏻 In Keycloak:

    - Client that allows authentication using client credentials.
    - Group named PROVIDERS. --> users: provider1 | provider2
    - Client that allows authentication using client credentials.
 
👉🏻 In bills-service:

    - Endpoint to create bills using Postman. Restrictions --> Only users belonging to the PROVIDERS group can register invoices. Read the list of groups from the JWT to validate in the controller.
    - Endpoint to search for bills by user ID. Restrictions --> Authenticated users only.
 
👉🏻 In users-service:

    - Endpoint to search for a user and their bills.
    - Endpoint to search users by ID using Keycloak REST Admin Client.
    - To search for bills, we use Feign, configuring the Feign client to authenticate and obtain the Keycloak token when sending the request.

👉🏻 In API Gateway:

    - Adding User microservice to the routing.
 
## ENDPOINTS:

Bring all bills - role user  --> http://localhost:8090/api/v1/bills/all

    - User: gisela
    - Password: password


Create new bill - group providers --> Postman:  POST -> http://localhost:8086/bills/create

    BODY:
    json
    
    {
        "customerBill": "d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9",
        "productBill": "courses/Java",
        "totalPrice": 2400.0
    }
    
    - Authorization token needed. Must be provider1 or provider2.


Find Bill by userId - no role or group needed, only authenticated user -->

http://localhost:8090/api/v1/bills/find/d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9
or
http://localhost:8090/api/v1/users/find/d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9

    - User: cosme
    - Password: password

__________________________________________________________________________________________
SPANISH
__________________________________________________________________________________________

## Despliegue de Microservicios ⚙️ 🚀👾
Configuración del Entorno:

    Docker Desktop: Asegúrate de tener Docker Desktop instalado para una gestión eficiente de contenedores.
    
    Config-server: Inicia el servicio de configuración centralizada para una administración coherente de las configuraciones.
    
    Ms-discovery: Despliega el servicio de descubrimiento para mantener la disponibilidad y escalabilidad de microservicios.
    
    Ms-bills: Implementa el microservicio de facturación diseñado para gestionar eficazmente las facturas de los clientes. Utilizando Spring Boot 3.1.2 y Java 17.
    
    Ms-gateway: Lanza el API Gateway, actuando como cliente de Keycloak.
    
    Ms-users: Otro microservicio cliente de Keycloak para funcionalidades adicionales.

## Configuración de Seguridad con Keycloak ⚠️
Usuarios y Roles:

    - gisela (con el rol "USER") - Acceso privilegiado al listado de facturas.
    - cosme (sin rol) - Sin acceso a la visualización de todas las facturas.
    - provider1 (sin rol pero en el grupo PROVIDERS)
    - provider2 (sin rol pero en el grupo PROVIDERS)

Tareas Adicionales:
Continuando con el proyecto del repositorio https://github.com/Gigi-U/keycloak-springboot3-application-part1 , ahora trabajamos en el sistema de facturación. Para esta etapa, vamos a trabajar en dos funcionalidades nuevas:

    - Los diferentes proveedores de facturas pueden dar de alta facturas.
    - Los usuarios pueden buscar sus facturas.

👉🏻 En Keycloak:

    - Creación de grupo llamado PROVIDERS.
    - Asignación de usuarios a este nuevo grupo.
    - Creación de cliente que permita autenticarse utilizando las credenciales del cliente (client credentials).
    
👉🏻 En bills-service:

    - Endpoint que permite dar de alta facturas. Restricciones --> Solo los usuarios pertenecientes al grupo PROVIDERS pueden dar de alta facturas. Leer del JWT el listado de grupos para luego validar en el controller.
    - Endpoint que nos permite buscar facturas por ID de usuario. Restricciones --> Usuarios autenticados.
 
👉🏻 En ms-users:

    - microservicio y endpoint que nos permite buscar a un usuario y sus facturas.
    - Búsqueda de usuario por ID utilizando Keycloak REST Admin Client.
    - Para buscar las facturas, usamos Feign. Configuramos el cliente de Feign para autenticarse y obtener el token de Keycloak cuando enviamos la petición.
 
👉🏻 En API Gateway:

    - Ruteo de microservicio de usuarios.
    
## ENDPOINTS:

Traer todas las facturas - rol usuario --> http://localhost:8090/api/v1/bills/all

    - Usuario: gisela
    - Contraseña: password
    
Crear nueva factura - grupo proveedores --> Postman: POST -> http://localhost:8086/bills/create

    CUERPO ->
    json
    
    {
        "customerBill": "d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9",
        "productBill": "courses/Java",
        "totalPrice": 2400.0
    }
    - Se necesita un token de autorización. Debe ser provider1 o provider2.
    
Buscar factura por ID de usuario - sin rol o grupo necesario, sólo debe ser usuario autenticado

http://localhost:8090/api/v1/bills/find/d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9
o
http://localhost:8090/api/v1/users/find/d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9

    - Usuario: cosme
    - Contraseña: password
