
# **Edu-Ping**

---


This project demonstrates a full-stack application with:
- **Frontend:** Angular
- **Backend:** Java with Spring Boot
- **Database:** MySQL

All services are containerized using **Docker Compose**.

---

## **Getting Started**

### **Prerequisites**
Ensure the following are installed on your machine:
- [Docker](https://www.docker.com/get-started)


---

## **How to Run**

1. Clone this repository:
   ```
   git clone git@github.com:HercVs/ping-spr.git
   ```

2. Clone the [frontend repository](https://github.com/HercVs/ping-ang) in the same root directory:
   ```
   git clone git@github.com:HercVs/ping-ang.git
   ```

---

```
project-root/
├── ping-spr/        # Backend
│   ├── Dockerfile
│   └── docker-compose.yml
└── ping-ang/         # Frontend
    └── Dockerfile
```

---

3. Build and run the containers:
   ```
   cd ping-spr
   docker-compose up --build
   ```

4. Access the application:
    - **Angular Frontend**: [http://localhost:4200](http://localhost:4200)
    - **Spring Backend**: [http://localhost:8080](http://localhost:8080)
    - **MySQL DB:** [http://localhost:3306](http://localhost:3306)

---

## **Default Values**
All default values are inserted in environment and can be edited in `docker-compose.yml`.

`EDUPING_JWT_SECRET_KEY` has publicly visible fallback value and can be left empty for testing.

### **MySQL**
Official MySQL docker image used for the DB service. Values in the backend and db services must match.

| Purpose          | Backend Service      | DB Service     | Default Value |
|------------------|----------------------|----------------|---------------|
| Database name    | EDUPING_TEST_DB_NAME | MYSQL_DATABASE | edupingdb     |
| DB user username | EDUPING_TEST_DB_USER | MYSQL_USER     | eduDBUser     |
| DB user password | EDUPING_TEST_DB_PASS | MYSQL_PASSWORD | pass@Edu123   |

---

## **Documentation**
- OpenAPI documentation available in:
  `http://localhost:8080/api-docs`

---

## **License**
This project is licensed under the MIT