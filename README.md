# Temporal Use Case Showcase

This project demonstrates various use cases of the Temporal workflow engine using Kotlin and Spring Boot.

## Overview

This initial version showcases a simple orchestrated workflow with the following steps:

1.  Simulates making an API call.
2.  Simulates performing a database operation.
3.  Simulates publishing a message to Kafka.

The project includes a Docker Compose setup for easily running the Temporal server and UI.

## Getting Started

### Prerequisites

* Docker
* Java Development Kit (JDK)
* Gradle

### Running the Project

1.  **Clone the repository** (once you've created it).
2.  **Start the Temporal environment** using Docker Compose:
    ```bash
    docker-compose up -d
    ```
    (This assumes you are using a `docker-compose.yml` similar to the one we discussed, possibly from the Temporal repository).
3.  **Build the Spring Boot application:**
    ```bash
    ./gradlew build
    ```
4.  **Run the Spring Boot application:** You can run it from your IDE.
5.  **Trigger the workflow:** Send a POST request to the following endpoint:
    ```
    POST http://localhost:8080/api/workflows/simple-orchestration
    ```
    You can use a tool like Bruno for this (a Bruno collection is included in the repository).

## Project Structure

```
├── dynamicconfig/
├── src/main/kotlin/com/hannyudayana/demo/temporalusecase/
│   ├── activities/
│   │   ├── ApiService.kt
│   │   ├── DatabaseService.kt
│   │   └── KafkaService.kt
│   ├── activities/impl/
│   │   ├── ApiServiceImpl.kt
│   │   ├── DatabaseServiceImpl.kt
│   │   └── KafkaServiceImpl.kt
│   ├── config/
│   │   └── TemporalWorkerConfig.kt
│   ├── controller/
│   │   └── WorkflowController.kt
│   ├── workflows/
│   │   └── SimpleOrchestrationWorkflow.kt
│   └── workflows/impl/
│       └── SimpleOrchestrationWorkflowImpl.kt
├── build.gradle.kts
├── docker-compose.yml
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```