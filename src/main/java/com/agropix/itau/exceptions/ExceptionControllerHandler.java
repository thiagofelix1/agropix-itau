package com.agropix.itau.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {

        String userMessage = ex.getMessage();
        String developerMessage = ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ItemNotExistsException.class})
    public ResponseEntity<Object> handleItemNotExistsException(ItemNotExistsException ex, WebRequest request) {
        String userMessage = ex.getMessage();
        String developerMessage = ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({FalhaComunicacaoBacenException.class})
    public ResponseEntity<Object> handleFalhaComunicacaoBacenException(FalhaComunicacaoBacenException ex, WebRequest request) {
        String userMessage = ex.getMessage();
        String developerMessage = ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ChavePixExistenteException.class})
    public ResponseEntity<Object> handleChavePixExistenteException(ChavePixExistenteException ex, WebRequest request) {
        String userMessage = ex.getMessage();
        String developerMessage = ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    private static class Error {

        private final String userMessage;
        private final String developerMessage;

        public Error(String userMessage, String developerMessage) {

            this.userMessage = userMessage;
            this.developerMessage = developerMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

    }
}
