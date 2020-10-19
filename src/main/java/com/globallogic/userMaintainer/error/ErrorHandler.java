package com.globallogic.userMaintainer.error;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.globallogic.userMaintainer.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    public static final Logger log  =  LoggerFactory.getLogger(ErrorHandler.class);
    private static ErrorDTO errorDTO;

    @Autowired
    public ErrorHandler(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    @ExceptionHandler(value = {MismatchedInputException.class,MethodArgumentNotValidException.class})
    public ResponseEntity badRequest(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        fieldErrors.forEach(f -> {
            errorDTO.setMensaje(f.getField() + " " + f.getDefaultMessage());
        });

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleMissingParams(MissingServletRequestParameterException ex) {
        errorDTO.setMensaje(ex.getParameterName() + " parameter is missing");

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity handleTypeMismatchException(TypeMismatchException ex) {
        errorDTO.setMensaje(ex.getValue().toString() + " parameter not allowed");

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(NoSuchElementException ex) {

        errorDTO.setMensaje(ex.getMessage());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
