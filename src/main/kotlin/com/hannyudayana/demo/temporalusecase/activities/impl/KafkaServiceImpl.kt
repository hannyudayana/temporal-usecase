package com.hannyudayana.demo.temporalusecase.activities.impl

import com.hannyudayana.demo.temporalusecase.activities.KafkaService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class KafkaServiceImpl : KafkaService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun publishKafkaMessage(message: String): String {
        logger.info("Executing publishKafkaMessage activity with message: $message")
        // Simulate publishing a Kafka message
        Thread.sleep(1000)
        logger.info("publishKafkaMessage activity completed")
        return "Kafka message published: $message"
    }
}
