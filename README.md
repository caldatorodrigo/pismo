## STEPS FOR THIS SPRING BOOT APP
- Define dependencies in build.gradle
- Create a Spring Boot Main @SpringBootApplication
- Create components @Entity / @RestController / @Repository
- Create application.properties
- Build 

## BUILD the application 
gradle clean build

## BUILD AND UP Docker Compose 
docker-compose up 
docker-compose down

## SWAGGER
http://localhost:8080/swagger-ui.html

## CURLS 

### POST /user/save 
curl -s -X POST \
  http://localhost:8080/user/save \
  -H 'Content-Type: application/json' \
  -d '{"name":"Your Name"}'


### GET /user/{id}
curl -s -X GET \
  http://localhost:8080/user/1 
 
 
