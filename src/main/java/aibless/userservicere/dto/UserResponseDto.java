package aibless.userservicere.dto;

import aibless.userservicere.model.Role;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class UserResponseDto {

    private int id;
    private String name;
    private Integer age;
    private Boolean isActive;
    private List<Role> roles;
}
