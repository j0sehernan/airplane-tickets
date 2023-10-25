package com.healthtools.airplaneticketsms.exceptions

import com.healthtools.airplaneticketsms.dtos.responses.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity(ex.message?.let { ExceptionResponse(it) }, HttpStatus.NOT_FOUND)
    }
}
