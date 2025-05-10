package com.hannyudayana.demo.temporalusecase.workflows

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface SimpleOrchestrationWorkflow {
    @WorkflowMethod
    fun execute(): String
}
