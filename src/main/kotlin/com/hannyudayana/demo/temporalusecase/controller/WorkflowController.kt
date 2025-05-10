package com.hannyudayana.demo.temporalusecase.controller

import com.hannyudayana.demo.temporalusecase.dto.WorkflowResultDto
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
    fun startSimpleOrchestration(): ResponseEntity<WorkflowResultDto> {
        val options = WorkflowOptions.newBuilder()
            .setTaskQueue("simple-orchestration-queue")
            .build()

        val workflow: SimpleOrchestrationWorkflow = workflowClient.newWorkflowStub(
            SimpleOrchestrationWorkflow::class.java,
            options
        )

        val result = workflow.execute()

        val response = WorkflowResultDto(message = "Workflow started and completed successfully", result = result)
        return ResponseEntity.ok(response)    }
}
