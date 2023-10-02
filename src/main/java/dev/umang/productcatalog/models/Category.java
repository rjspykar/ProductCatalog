package dev.umang.productcatalog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Category extends BaseModel {

      private String name;

      public  Category(){}
      public Category(String name) {
            this.name = name;
      }
}
