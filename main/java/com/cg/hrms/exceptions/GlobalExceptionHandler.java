package com.cg.hrms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        ErrorResponse er = new ErrorResponse.Builder()
        		.withStatus(HttpStatus.NOT_FOUND.value())
        		.withMessage(exception.getMessage())
        		.build();
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFoundException(DepartmentNotFoundException exception) {
    	   ErrorResponse er = new ErrorResponse.Builder()
           		.withStatus(HttpStatus.NOT_FOUND.value())
           		.withMessage(exception.getMessage())
           		.build();
    	return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException exception) {
    	   ErrorResponse er = new ErrorResponse.Builder()
           		.withStatus(HttpStatus.BAD_REQUEST.value())
           		.withMessage(exception.getMessage())
           		.build();
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
    	ErrorResponse er = new ErrorResponse.Builder()
           		.withStatus(HttpStatus.CONFLICT.value())
           		.withMessage(exception.getMessage())
           		.build();
        return new ResponseEntity<>(er, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception) {
    	ErrorResponse er = new ErrorResponse.Builder()
           		.withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
           		.withMessage(exception.getMessage())
           		.build();
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    //general exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .withStatus(HttpStatus.CONFLICT.value())
                .withMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException ex) {
        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
