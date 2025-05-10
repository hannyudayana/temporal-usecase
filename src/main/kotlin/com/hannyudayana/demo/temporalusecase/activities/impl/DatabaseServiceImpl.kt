package com.hannyudayana.demo.temporalusecase.activities.impl

import com.hannyudayana.demo.temporalusecase.activities.DatabaseService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DatabaseServiceImpl : DatabaseService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun performDatabaseOperation(): String {
        logger.info("Executing performDatabaseOperation activity")
        // Simulate a database operation
        Thread.sleep(3000)
        logger.info("performDatabaseOperation activity completed")
        return "Database operation successful"
    }
}
