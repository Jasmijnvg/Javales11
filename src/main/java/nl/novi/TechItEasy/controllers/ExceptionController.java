package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String>
    handleIndexOutofBoundsException(IndexOutOfBoundsException exception) {
        return new ResponseEntity<>("Index out of bounds: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

