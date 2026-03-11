# Apache Camel Study
Created on: 2026-03-08

Step-by-step learning project based on the Udemy course **"Learn Apache Camel Framework with Spring Boot"**. (Notes in: `./Courses/Udemy-Learn Apache Camel Framework with Spring Boot`)

## To enable queue communication between microservices A and B, ensure the following service is running:
### ActiveMQ
Run: `docker run -p 61616:61616 -p 8161:8161 rmohr/activemq` <br>
Login: <br>
URL: http://localhost:8161/admin/ <br>
User: admin <br>
Password: admin <br>

### Kafka
Run: `docker compose up`
(File in root path: `docker-compose.yml`) <br>
Login: <br>
URL: http://localhost:9000/ <br>