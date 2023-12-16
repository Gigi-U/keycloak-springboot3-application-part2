# urriza_y_spreafichi_gisela_tpfinal_eb2

⚠️LEVANTAR MICROSERVICIOS ⚠️

PASOS:

    1.	Docker Desktop
    2.	Config-server
    3.	Ms-discovery
    4.	Ms-bills
    5.	Ms-gateway - es keycloak client
    6.  Ms-users   - es keycloak client

⚠️Keycloak Users ⚠️

    1. gisela (con rol) - acceso a listado
    2. cosme (sin rol) - sin acceso a la visualización de todas las facturas.
    3. provider1 (sin rol pero en grupo PROVIDERS)
    4. provider2 (sin rol pero en grupo PROVIDERS)

Trabajo práctico
TASKS
Continuando con la tarea propuesta en el trabajo práctico parcial, ahora trabajamos en el
sistema de facturación. Para esta etapa vamos a trabajar en dos funcionalidades nuevas:

✔️ Los diferentes proveedores de facturas podrán dar de alta facturas.
✔️ Los usuarios podrán buscar sus facturas.

👉🏻 En Keycloak:
- [x] Crear un grupo llamado PROVIDERS.
- [x] Asignar usuarios a este nuevo grupo.
- [x] Crear un cliente que permita autenticarse utilizando las credenciales del cliente
(client credentials).

👉🏻 En bills-service:
- [x] Crear un endpoint que nos permita dar de alta facturas. Restricciones:
    - [ ] Solo los usuarios pertenecientes al grupo PROVIDERS podrán dar de alta facturas. Leer del JWT el listado de grupos para luego validar en el controller.
- [x] Crear un endpoint que nos permita buscar facturas por ID de usuario. Restricciones:
    - [x] Usuarios autenticados. 

👉🏻 En users-service:
- [x] Crear el microservicio y agregar un endpoint que nos permita buscar a un usuario y
sus facturas.
- [x] Buscar el usuario por ID utilizando Keycloak REST Admin Client.
- [ ] Para buscar las facturas, utilizar Feign. Configurar el cliente de Feign para
autenticarnos y obtener el token de Keycloak cuando enviamos la petición.

👉🏻 En API Gateway:
- [x] Agregar al ruteo el microservicio de usuarios.


------------------------------------------------------------------------------------------
ENDPOINTS: 
------------------------------------------------------------------------------------------
Bring all bills - role user
-  http://localhost:8090/api/v1/bills/all
    user -> gisela  password: password

Create new bill - group providers
- Postman:     
    POST -> http://localhost:8086/bills/create
    BODY ->
    {
    "customerBill": "d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9",
    "productBill": "courses/Java",
    "totalPrice": 2400.0
    }
Authorization token needed . must be provider1 or provider2 


Find Bill by userId - no role or group needed, only authenticated user    
- http://localhost:8090/api/v1/bills/find/d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9 
or 
- http://localhost:8090/api/v1/users/find/d94704ec-c9f5-48f2-9f4a-3e6bd57ad0c9  
 
    user -> cosme  password: password
