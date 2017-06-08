FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/board-game-local.jar /board-game-local/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/board-game-local/app.jar"]
