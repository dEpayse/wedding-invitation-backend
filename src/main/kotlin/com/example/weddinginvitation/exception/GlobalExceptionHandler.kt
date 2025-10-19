package com.example.weddinginvitation.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

import mu.KotlinLogging


@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = KotlinLogging.logger {  }

    @ExceptionHandler(ServerException::class)
    fun handleServerException(e: ServerException) : ErrorResponse {
        logger.error { e.message }

        return ErrorResponse(
            code = e.code,
            message = e.message
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception) : ErrorResponse {
        logger.error { e.message }

        return ErrorResponse(
            code = 500,
            message = "Internal Server Error"
        )
    }
}