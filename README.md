# Microservices usando quarkus
- Para subir as aplicações:
``` 
 mvn clean compile quarkus:dev -Ddebug=5005
```
- Deploy em kubernetes
``` 
mvn clean package -Dquarkus.container-image.build=true -Dquarkus.kubernetes.deploy=true -Dquarkus.container-image.push=true
``` 
