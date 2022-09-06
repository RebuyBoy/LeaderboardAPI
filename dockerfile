FROM openjdk:17
ADD /target/leaderboard-0.0.1-SNAPSHOT.jar leaderboardAPI.jar
ENTRYPOINT ["java","-jar","leaderboardAPI.jar"]
