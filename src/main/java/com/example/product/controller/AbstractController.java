package com.example.product.controller;

import com.example.product.exception.ErrorResponse;
import com.example.product.exception.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
@RequestMapping("/api/v1/products")
public abstract class AbstractController {

    protected ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected <T> ResponseEntity<SuccessResponse<T>> createSuccessResponse(T body, String message, HttpStatus status) {
        SuccessResponse<T> successResponse = new SuccessResponse<>(status, message, body);
        return new ResponseEntity<>(successResponse, status);
    }

}
