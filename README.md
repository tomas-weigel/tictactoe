# tictactoe-spring-boot
Sample Spring Boot Tictactoe Application

## run database
`docker run --name postgres -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -p 127.0.0.1:5432:5432 -d postgres`  

## run server
`mvn install`
`java -jar target/tictactoe-spring-boot-0.0.1-SNAPSHOT.jar`

## play simple game
`curl 'http://localhost:8080/game/joinBattle'`  
`curl 'http://localhost:8080/game/joinBattle'`  
`curl 'http://localhost:8080/game/1/placeMarker/X/1/0'`  
`curl 'http://localhost:8080/game/1/placeMarker/O/0/0'`  
`curl 'http://localhost:8080/game/1/placeMarker/X/1/1'`  
`curl 'http://localhost:8080/game/1/placeMarker/O/0/1'`  
`curl 'http://localhost:8080/game/1/placeMarker/X/1/2'`  