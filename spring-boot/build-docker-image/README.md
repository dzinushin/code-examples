# Build spring boot image by right way

https://www.youtube.com/watch?v=hAHXp_jQWVo
https://odedia.org/production-considerations-for-spring-on-kubernetes

https://www.baeldung.com/docker-layers-spring-boot


```
java -Djarmode=layertools -jar build-docker-image.jar list

java -Djarmode=layertools -jar build-docker-image.jar extract
```


```
./gradlew bootBuildImage --imageName=build-docker-image:1.0
```
