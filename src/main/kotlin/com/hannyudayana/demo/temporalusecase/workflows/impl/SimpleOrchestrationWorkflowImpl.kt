package com.hannyudayana.demo.temporalusecase.workflows.impl

import com.hannyudayana.demo.temporalusecase.activities.ApiService
import com.hannyudayana.demo.temporalusecase.activities.DatabaseService
import com.hannyudayana.demo.temporalusecase.activities.KafkaService
import com.hannyudayana.demo.temporalusecase.workflows.SimpleOrchestrationWorkflow
import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Workflow
import java.time.Duration

class SimpleOrchestrationWorkflowImpl : SimpleOrchestrationWorkflow {
    private val apiService = Workflow.newActivityStub(
        ApiService::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .setRetryOptions(
                RetryOptions.newBuilder()
                    .setMaximumAttempts(5)
                    .setInitialInterval(Duration.ofSeconds(1))
                    .setBackoffCoefficient(2.0)
                    .build()
            )
            .build()
    )

    private val databaseService = Workflow.newActivityStub(
        DatabaseService::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .setRetryOptions(
                RetryOptions.newBuilder()
                    .setMaximumAttempts(5)
                    .setInitialInterval(Duration.ofSeconds(1))
                    .setBackoffCoefficient(2.0)
                    .build()
            )
            .build()
    )

    private val kafkaService = Workflow.newActivityStub(
        KafkaService::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(10))
            .setRetryOptions(
                RetryOptions.newBuilder()
                    .setMaximumAttempts(3)
                    .setInitialInterval(Duration.ofSeconds(1))
                    .setBackoffCoefficient(1.0) // No backoff
                    .build()
            )
            .build()
    )

    override fun execute(): String {
        val apiResult = apiService.makeApiCall()
        println("API Result: $apiResult")

        val dbResult = databaseService.performDatabaseOperation()
        println("Database Result: $dbResult")

        val kafkaResult = kafkaService.publishKafkaMessage("Data from API: $apiResult, DB: $dbResult")
        println("Kafka Result: $kafkaResult")

        return "Workflow completed successfully. Kafka message: $kafkaResult"
    }
}
