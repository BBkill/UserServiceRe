package aibless.userservicere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    //@NotBlank(message = "name can not be null")
    @Column(name = "name")
    private String name;

    //@Min(value = 17, message = "must be over 18")
    //@Max(value = 100, message = "to damn old")
    @Column(name = "age")
    private Integer age;

    //@NotBlank(message = "email can not be null")
    @Column(name = "email")
    private String email;

}
