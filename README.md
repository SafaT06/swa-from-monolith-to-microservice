# Monolith: Shop Demo (Spring Boot)

Een kleine monoliet met:
- **Auth**: register/login (dummy, geen echte security)
- **Productcatalogus**: CRUD
- **Orders**: order plaatsen (controleert productvoorraad), simple status
- **Payments**: gesimuleerde betaling

## Quickstart

```bash
./mvnw spring-boot:run
```

H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)

### Endpoints (selectie)
POST /api/auth/register { "username": "alice", "password": "pw" }
POST /api/auth/login { "username": "alice", "password": "pw" }
GET /api/products
POST /api/products { "name": "Book", "description": "SWA", "price": 19.99, "stock": 5 }
POST /api/orders { "userId": 1, "items": [{ "productId": 1, "quantity": 2 }]}
GET /api/orders/{id}
