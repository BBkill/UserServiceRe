package aibless.userservicere.dto;

import aibless.userservicere.validator.constrain.ContactNumberConstraint;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)//AUTO_INCREMENT
    private Integer id;

    @NotBlank(message = "name can not be null")
    private String name;

    @Min(value = 17, message = "must be over 18")
    @Max(value = 100, message = "to damn old")
    private Integer age;

    @NotBlank(message = "email can not be null")
    private String email;

    @ContactNumberConstraint
    @NotBlank(message = "pass word must not be null")
    private String phoneNumber;

    @NotBlank(message = "pass word must not be null")
    private String passWord;
}
