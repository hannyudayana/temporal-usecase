package com.hannyudayana.demo.temporalusecase.config

import com.hannyudayana.demo.temporalusecase.activities.impl.ApiServiceImpl
import com.hannyudayana.demo.temporalusecase.activities.impl.DatabaseServiceImpl
import com.hannyudayana.demo.temporalusecase.activities.impl.KafkaServiceImpl
import com.hannyudayana.demo.temporalusecase.workflows.impl.SimpleOrchestrationWorkflowImpl
import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.Worker
import io.temporal.worker.WorkerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TemporalWorkerConfig {

    private val namespace = "default"
    private val taskQueue = "simple-orchestration-queue"

    @Autowired
    private lateinit var apiService: ApiServiceImpl

    @Autowired
    private lateinit var databaseService: DatabaseServiceImpl

    @Autowired
    private lateinit var kafkaService: KafkaServiceImpl

    @Bean
    fun workflowServiceStubs(): WorkflowServiceStubs {
        return WorkflowServiceStubs.newLocalServiceStubs()
    }

    @Bean
    fun workflowClient(workflowServiceStubs: WorkflowServiceStubs): WorkflowClient {
        return WorkflowClient.newInstance(workflowServiceStubs)
    }

    @Bean
    fun worker(workflowClient: WorkflowClient): Worker {
        val workerFactory = WorkerFactory.newInstance(workflowClient)
        val worker = workerFactory.newWorker(taskQueue)
        worker.registerWorkflowImplementationTypes(SimpleOrchestrationWorkflowImpl::class.java)
        worker.registerActivitiesImplementations(apiService, databaseService, kafkaService)
        workerFactory.start()
        return worker
    }
}
