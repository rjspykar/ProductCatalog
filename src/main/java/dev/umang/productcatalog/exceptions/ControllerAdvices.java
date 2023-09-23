package dev.umang.productcatalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.umang.productcatalog.dtos.ExceptionDTO;


@ControllerAdvice
public class ControllerAdvices {
      

      // @ExceptionHandler(NotFoundException.class)
      // private ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
      //       return new ResponseEntity<ExceptionDTO>(
      //             new ExceptionDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage()), 
      //             HttpStatus.NOT_FOUND);
      // }
}
