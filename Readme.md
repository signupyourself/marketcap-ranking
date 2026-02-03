# Marketcap Ranking Application
An application that demonstrates microservices architecture pattern for big data. Consists of a webapp and two background processes. The application ranks companies and countries by market cap.
### Components
* Web application
* Collector
* Analyzer

### Technology stack

## Getting Started
1. Setting up environment variables

   This project requires you to setup seven environment variables
    * Application related environment variables

          JDBC_DATABASE_URL="jdbc:postgresql://localhost:5432/Data"
          JDBC_DATABASE_PASSWORD="{Set any password for db}"
          JDBC_DATABASE_USERNAME="{Set any username for db}"
          JDBC_DATABASE_NAME="Data"

    * Flyway related environment variables

          FLYWAY_USER="{use the same username as above}"
          FLYWAY_PASSWORD="{use the same password as above}"
          FLYWAY_URL="jdbc:postgresql://localhost:5432/Data"

1. Starting docker

        docker compose up

1. Applying test database migrations

       flyway -user=admin -password=1234 -url="jdbc:postgresql://localhost:5433/Test" -locations= "filesystem:database/migrations" -cleanDisabled=false clean migrate

1. Applying production database migrations

        flyway -locations="filesystem:database/migrations" -cleanDisabled=false clean migrate

1. Build the application

        ./gradlew clean build

1. Running the collector application

         java -jar applications/collector/build/libs/collector-all.jar

1. Running the analyzer application

         java -jar applications/analyzer/build/libs/analyzer-all.jar

1. Running the web application

         java -jar applications/webapp/build/libs/webapp.war