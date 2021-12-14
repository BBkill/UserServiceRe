package aibless.userservicere.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDto {

    private Integer id;

    @NotBlank(message = "name can not be null")
    private String name;

    @Min(value = 17, message = "must be over 18")
    @Max(value = 100, message = "to damn old")
    private Integer age;

    @NotBlank(message = "email can not be null")
    private String email;

}
