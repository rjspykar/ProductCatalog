package dev.umang.productcatalog.models;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Product extends BaseModel {

      private String title;
      private String description;
      private String image;
      private Category category;
      private double price;
      
}
