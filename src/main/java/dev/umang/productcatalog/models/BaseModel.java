package dev.umang.productcatalog.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
      @Id
      @GeneratedValue(generator = "uuidgenerator")
      @GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
      private UUID uuid;
      
}
