## Payment System

### High level diagram

![payment-system drawio](https://user-images.githubusercontent.com/5902646/171903906-57e173b2-fddd-4da0-98e4-c04b2611d211.png)



### this payment system is consisted of the following services :
1. Payment Service : this service capture payment events
2. User Service: this service going to keep user (buyer,merchant) data
3. Authentication Service: this service will manage user authentication and authorization
4. Ledger Service: this service will capture fund movement (debit, credit)
5. Wallet Service: this service will handle user balance
6. Risk Service: this service going to handle risk check for each payment attempt
7. Notification Service: this service going to handle any payment notification related things like push notification, sms, email
8. Discovery Service: this is discovery server which will contains all informations regarding all services instances
9. Gateway Service: this is going to be microservice api gateway


### Payment service design
This service has the following apis:
- POST /{version}/order
  This endpoint is used for creating order
- POST /{version}/payment
  This endpoint is used when doing order checkout and proceed payment
- DELETE /{version}/order 
  This endpoint is used for canceling order
- POST /{version}/refunds/{payment-id}
  This endpoint is used for refunding payments
- GET /{version}/payment/{payment-id}
  This endpoint is used for getting payment state detail

### User service design
This service has the following apis:
- POST /{version}/user
  This endpoint for registering new user
- PUT /{version}/user
  This endpoint for registering new user
- GET /{version}/user/{user-identifier}
  This endpoint for getting user detail information
  
### Authentication service Design
This service has the following apis:
- POST /{version}/auth

### Ledger Service Design
This service has the following apis:
- POST /{version}/ledger
  This endpoint for recording fund movement data (debit,credit)
- GET /{version}/ledger/{user-id}?start={start_date}&end={end_date}

###  Wallet Service Design
This service has the following apis:
- GET /{version}/wallet/balance
  This endpoint for getting user balance
- POST /{version}/wallet/debit
  This endpoint for debitting from user balance to system bank account
- POST /{version}/wallet/credit
  This endpoint for crediting to user balance from system bank account


### Risk Service
This service has the following apis:
- POST /{version}/risk
  This endpoint for getting user balance

### Discovery Service
  Will use Eureka from spring cloud stack for microservice discovery

### API Gateway
  Will use spring cloud gateway for routing the request to correct service

