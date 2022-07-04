# Microservics Architecture
This is sample for Microservice eComm application

### Endpoints :-

microservice service endpoints
- Eureka
http://localhost:8761/eureka or 
http://localhost:8761

- Order
http://localhost:8083/order/getMessage
http://localhost:8083/order/orders
http://localhost:8083/order/1

- User
http://localhost:8081/user	## /users, /{userId}
http://localhost:8081/user/1

- Product
http://localhost:8082/product 	## /products, /{code}
http://localhost:8082/product/products
http://localhost:8082/product/P001


- Config - http://localhost:5555/customer-service/dev
- Hystrix Dashboard - http://localhost:8788/hystrix



### URL's through edge proxy :-
-  http://localhost:8090/ecomm/catalog-service/catalog/products
-  http://localhost:8090/ecomm/catalog-service/catalog/product/P001
-  http://localhost:8090/ecomm/order-service/order/allOrders
-  http://localhost:8090/ecomm/order-service/order/ORD_001
-  http://localhost:8090/ecomm/user-account-service/userAccount/users
-  http://localhost:8090/ecomm/user-account-service/userAccount/user/guru


https://medium.com/swlh/spring-boot-webclient-cheat-sheet-5be26cfa3e
