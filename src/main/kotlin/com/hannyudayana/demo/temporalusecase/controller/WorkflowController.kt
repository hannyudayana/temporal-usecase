package com.hannyudayana.demo.temporalusecase.controller

import com.hannyudayana.demo.temporalusecase.workflows.SimpleOrchestrationWorkflow
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/workflows")
class WorkflowController {

    @Autowired
    private lateinit var workflowClient: WorkflowClient

    @PostMapping("/1-simple-orchestration")
    fun startSimpleOrchestration(): ResponseEntity<String> {
        val options = WorkflowOptions.newBuilder()
            .setTaskQueue("simple-orchestration-queue")
            .build()

        val workflow: SimpleOrchestrationWorkflow = workflowClient.newWorkflowStub(
            SimpleOrchestrationWorkflow::class.java,
            options
        )

        val result = workflow.execute()

        return ResponseEntity.ok("Workflow started and completed with result: $result")
    }
}
