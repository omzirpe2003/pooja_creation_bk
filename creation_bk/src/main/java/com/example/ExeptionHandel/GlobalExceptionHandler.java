package com.example.ExeptionHandel;

import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Model.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── Your previous handlers remain (validation, jwt expired, etc.) ──

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)           // 409 Conflict is perfect here
                .body(new ApiResponse<>(false, ex.getMessage(), null,ex.getErrorCode()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, ex.getMessage(), null,ex.getErrorCode()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(false, ex.getMessage(),null, ex.getErrorCode()));
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ApiResponse<Object>> handlePasswordMismatch(PasswordMismatchException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, ex.getMessage(), null,ex.getErrorCode()));
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<ApiResponse<Object>> handleTokenValidation(TokenValidationException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(false, ex.getMessage(), null,ex.getErrorCode()));
    }

    // Optional: Catch all ApplicationException children not handled above
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<Object>> handleApplicationException(ApplicationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, ex.getMessage(),null, ex.getErrorCode()));
    }
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<ProductNotFoundException>> handalProductNotFoundException(ProductNotFoundException ex){
    	return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, ex.getMessage(),null, ex.getErrorCode()));
    }
    
    @ExceptionHandler(UserAlreadyAddedReviewException.class)
     public ResponseEntity<ApiResponse<Object>> handleUserAlreadyAddedReviewException(UserAlreadyAddedReviewException ex){
    	return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, ex.getMessage(),null, ex.getErrorCode()));
     }
    
    
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleOrderNotFountException(OrderNotFoundException ex){
    	return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, ex.getMessage(),null, ex.getErrorCode()));
    }

    // Keep the generic Exception handler as fallback (500)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
//        // Log the exception in real app: log.error("Unhandled exception", ex);
//        return ResponseEntity
//                .internalServerError()
//                .body(new ApiResponse<>(false, "Internal server error", null));
//    }
}