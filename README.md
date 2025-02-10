
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

## **MySQL Default Values**
Default values are set in `docker-compose.yml`:
- **Database:** `edupingdb`
- **User:** `eduDBUser`
- **Password:** `pass@Edu123`

---

## **Documentation**
- OpenAPI documentation available in:
  `http://localhost:8080/api-docs`

---

## **License**
This project is licensed under the MIT