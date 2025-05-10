package com.hannyudayana.demo.temporalusecase.activities

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface ApiService {
    @ActivityMethod
    fun makeApiCall(): String
}