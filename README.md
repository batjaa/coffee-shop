# Coffee Shop

## Intro

Import as an existing maven project and run the Main.java as a Java application. This will do the trick ;)

Visit localhost:8080

## Swagger2

Run

```
java -jar swagger.jar generate -i http://localhost:8080/v2/api-docs  -l java --library=retrofit2 -DmodelPackage=rs.pscode.start.model,apiPackage=rs.pscode.start.api -o spring-boot-starter-genereated
```

And visit http://localhost:8080/swagger-ui.html

## Thanks to…

https://github.com/savicprvoslav

For an extensive full documentation please visit https://github.com/savicprvoslav/Spring-Boot-starter
Note that many feature has changed and this document should be only used as a reference.


