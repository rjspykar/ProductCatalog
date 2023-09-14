package dev.umang.productcatalog.dtos;


import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {
      public ExceptionDTO(HttpStatus notFound, String message) {
            this.errorCode=notFound;
            this.errorMessage=message;
      }
      private HttpStatus errorCode;
      private String errorMessage;

}
