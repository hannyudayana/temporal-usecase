package com.hannyudayana.demo.temporalusecase.activities.impl

import com.hannyudayana.demo.temporalusecase.activities.ApiService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ApiServiceImpl : ApiService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun makeApiCall(): String {
        logger.info("Executing makeApiCall activity")
        // Simulate an API call
        Thread.sleep(2000)
        logger.info("makeApiCall activity completed")
        return "API call successful"
    }
}
