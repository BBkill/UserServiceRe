package aibless.userservicere.mapper;

import aibless.userservicere.dto.UserRequestDto;
import aibless.userservicere.dto.UserResponseDto;
import aibless.userservicere.model.User;
import aibless.userservicere.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserMapper {


    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private  UserService userService;


    private User convertToEntity(UserRequestDto userRequestDto) {
        return modelMapper.map(userRequestDto, User.class);
    }

    public UserResponseDto findUserById(int id) {
        return convertToUserResponseDto(userService.getUser(id));
    }

    public UserResponseDto findUserByEmail(String email) {
        return convertToUserResponseDto(userService.getUser(email));
    }

    public UserResponseDto convertToUserResponseDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    public List<UserResponseDto> findUsers() {
        return userService.getAllUser().stream().map(this::convertToUserResponseDto).collect(Collectors.toList());
    }
}