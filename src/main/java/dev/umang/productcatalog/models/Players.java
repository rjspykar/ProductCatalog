package dev.umang.productcatalog.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Players {
    @Id
    private long id;
    private String name;
    private Integer age;
}
