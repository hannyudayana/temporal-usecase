package com.hannyudayana.demo.temporalusecase.activities

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface KafkaService {
    @ActivityMethod
    fun publishKafkaMessage(message: String): String
}
