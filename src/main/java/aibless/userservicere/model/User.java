package aibless.userservicere.model;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {


    @Id
    @Column(name = "id")
    private int id;

    //@NotNull(message = "name can not be null")
    @NotBlank(message = "name can not be null")
    @Column(name = "name")
    private String name;

    @Min(value = 17, message = "must be over 18")
    @Max(value = 100, message = "to damn old")
    @Column(name = "age")
    private Integer age;

    //@NotNull(message = "email can not be null")
    @NotBlank(message = "email can not be null")
    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email =email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && age.equals(user.age) && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
