package com.hannyudayana.demo.temporalusecase.activities

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface DatabaseService {
    @ActivityMethod
    fun performDatabaseOperation(): String
}
