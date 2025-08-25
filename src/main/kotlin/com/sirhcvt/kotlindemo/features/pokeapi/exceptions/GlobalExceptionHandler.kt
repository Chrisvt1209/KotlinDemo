package com.sirhcvt.kotlindemo.features.pokeapi.exceptions

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException): ResponseEntity<ErrorResponse> {
        if (LOGGER.isErrorEnabled) {
            LOGGER.error("NoSuchElementException: {}", ex.message, ex)
        }
        val message = if (ex.message == "No value present") "Resource not found" else ex.message ?: "Resource not found"
        return generateErrorResponseEntity(HttpStatus.NOT_FOUND, message)
    }

    @ExceptionHandler(DuplicateResourceException::class)
    fun handleDuplicateResourceException(ex: DuplicateResourceException): ResponseEntity<ErrorResponse> {
        if (LOGGER.isErrorEnabled) {
            LOGGER.error("DuplicateResourceException: {}", ex.message, ex)
        }
        return generateErrorResponseEntityFromException(HttpStatus.CONFLICT, ex, "Resource already exists")
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        if (LOGGER.isErrorEnabled) {
            LOGGER.error("IllegalArgumentException: {}", ex.message, ex)
        }
        return generateErrorResponseEntityFromException(HttpStatus.BAD_REQUEST, ex, "Invalid argument")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        if (LOGGER.isErrorEnabled) {
            LOGGER.error("MethodArgumentNotValidException: {}", ex.message, ex)
        }
        val errors = ex.bindingResult.allErrors
            .mapNotNull { error ->
                val fieldName = (error as? org.springframework.validation.FieldError)?.field
                val errorMessage = error.defaultMessage
                if (fieldName != null && errorMessage != null) "$fieldName $errorMessage" else null
            }
            .joinToString(", ")
        return generateErrorResponseEntity(HttpStatus.BAD_REQUEST, errors)
    }

    private fun getMessage(ex: Throwable, fallbackMessage: String): String =
        ex.message ?: fallbackMessage

    private fun generateErrorResponseEntityFromException(
        status: HttpStatus,
        ex: Throwable,
        fallbackMessage: String
    ): ResponseEntity<ErrorResponse> =
        generateErrorResponseEntity(status, getMessage(ex, fallbackMessage))

    private fun generateErrorResponseEntity(status: HttpStatus, message: String): ResponseEntity<ErrorResponse> {
        val timestamp = java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC)
            .format(java.time.format.DateTimeFormatter.ISO_INSTANT)
        return ResponseEntity(ErrorResponse(timestamp, status.name, status.value(), message), status)
    }
}