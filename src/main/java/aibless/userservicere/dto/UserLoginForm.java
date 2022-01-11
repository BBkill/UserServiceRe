package aibless.userservicere.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginForm {

    @NotBlank(message = "email can not be null")
    private String email;


    @NotBlank(message = "pass word must not be null")
    private String passWord;

    public String getEmail() {
        return email;
    }

    public String getPassWord() {
        return passWord;
    }
}
