
# StayEase

RESTful API service using Spring Boot to streamline the room booking process for a hotel management aggregator application. You are required to use MySQL to persist the data.


### ADMIN
Username `User1`
Password `password`

## Authors

- [@Omkrushana](https://www.github.com/omkrushana)
## API Reference

### AuthController API

#### Register Customer

Registers a new customer.

| Parameter       | Type     | Description           |
| :-------------- | :------- | :-------------------- |
| `email`         | `string` | **Required**. Email of the customer |
| `password`      | `string` | **Required**. Password of the customer |
| `firstName`     | `string` | **Required**. First name of the customer |
| `lastName`      | `string` | **Required**. Last name of the customer |

Example cURL:
```sh
curl --location 'http://localhost:8080/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "user@example.com",
    "password": "password",
    "firstName": "John",
    "lastName": "Doe"
}'
```

#### Customer Login


Registers a new customer.

| Parameter       | Type     | Description           |
| :-------------- | :------- | :-------------------- |
| `email`         | `string` | **Required**. Email of the customer |
| `password`      | `string` | **Required**. Password of the customer |


Example cURL:
```sh
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "user@example.com",
    "password": "password"
}'

```
Response:
<customer jwt token>

#### Register manager

Registers a new manager.

| Parameter       | Type     | Description           |
| :-------------- | :------- | :-------------------- |
| `email`         | `string` | **Required**. Email of the manager |
| `password`      | `string` | **Required**. Password of the manager |
| `firstName`     | `string` | **Required**. First name of the manager |
| `lastName`      | `string` | **Required**. Last name of the manager |

Example cURL:
```sh
curl --location 'http://localhost:8080/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "user@example.com",
    "password": "password",
    "firstName": "John",
    "lastName": "Doe"
}'
```

#### Customer Login


Registers a new customer.

| Parameter       | Type     | Description           |
| :-------------- | :------- | :-------------------- |
| `email`         | `string` | **Required**. Email of the customer |
| `password`      | `string` | **Required**. Password of the customer |


Example cURL:
```sh
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "user@example.com",
    "password": "password"
}'

```

Response:
<manager jwt token>

# HotelController API

### Get All Hotels

#### Retrieves a list of all hotels.

```http
GET /api/hotels
```
Example cURL:
```sh
curl --location 'http://localhost:8080/api/hotels' \
--header 'Content-Type: application/json'

```

### Get Hotel By ID

#### Retrieves a hotel by its ID.

| Parameter       | Type     | Description           |
| :-------------- | :------- | :-------------------- |
| `id`         | `Long` | **Required**. ID of the hotel
 
Example cURL:

```
curl --location 'http://localhost:8080/api/hotels/{id}' \
--header 'Content-Type: application/json'
```

### Add New Hotel

#### Adds a new hotel (Admin only).
use basic auth for admin (Use above Admin Credentials)

| Parameter     | Type     | Description                       |
| :------------ | :------- | :-------------------------------- |
| `name`        | `string` | **Required**. Name of the hotel   |
| `address`     | `string` | **Required**. Address of the hotel |
| `description` | `string` | **Required**. Description of the hotel |
| `rooms`       | `int`    | **Required**. Number of rooms in the hotel |

Example cURL:

```
curl --location 'http://localhost:8080/api/hotels/add' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {token}' \
--data-raw '{
    "name": "Hotel Example",
    "address": "123 Example St",
    "description": "A luxurious example hotel",
    "rooms": 100
}'

```
### Update Hotel

#### Updates an existing hotel (Manager only).

use <Token> generated for manager after manager login (Use above Admin Credentials)

| Parameter     | Type     | Description                       |
| :------------ | :------- | :-------------------------------- |
| `name`        | `string` | **Required**. Name of the hotel   |
| `address`     | `string` | **Required**. Address of the hotel |
| `description` | `string` | **Required**. Description of the hotel |
| `rooms`       | `int`    | **Required**. Number of rooms in the hotel |

Example cURL:

```
curl --location 'http://localhost:8080/api/hotels/add' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {token}' \
--data-raw '{
    "name": "Hotel Example",
    "address": "123 Example St",
    "description": "A luxurious example hotel",
    "rooms": 100
}'

```


