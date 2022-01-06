package aibless.userservicere.mapper;

import aibless.userservicere.dto.UserRequestDto;
import aibless.userservicere.dto.UserResponseDto;
import aibless.userservicere.model.User;
import aibless.userservicere.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

//
//    private User convertToEntity(UserRequestDto userRequestDto) {
//        return modelMapper.map(userRequestDto, User.class);
//    }
//
//    public UserResponseDto findUserById(int id) {
//        return convertToUserResponseDto(userService.getUser(id));
//    }
//
//    public UserResponseDto findUserByEmail(String email) {
//        return convertToUserResponseDto(userService.getUser(email));
//    }
//
//    public UserResponseDto findUserByPhoneNumber(String number)
//    {
//        return convertToUserResponseDto(userService.getUserByPhoneNumber(number));
//    }
//
//    public UserResponseDto convertToUserResponseDto(User user) {
//        return modelMapper.map(user, UserResponseDto.class);
//    }
//
//    public List<UserResponseDto> findUsers() {
//        return userService.getAllUser().stream().map(this::convertToUserResponseDto).collect(Collectors.toList());
//    }
//
//    public UserResponseDto addUser(UserRequestDto user) {
//        return modelMapper.map( userService.createUser(convertToEntity(user)), UserResponseDto.class );
//    }
//
//    public UserResponseDto deleteUser(String email) {
//        return modelMapper.map(userService.deleteUser(userService.getUser(email)), UserResponseDto.class);
//    }
//
//    public UserResponseDto updateUser(UserRequestDto user) {
//        return modelMapper.map(userService.updateUser(convertToEntity(user)), UserResponseDto.class);
//    }
////
////    public List<UserResponseDto> findListUsers(String constraint) {
////        return userService.getByConstraint(constraint).stream().map(this::convertToUserResponseDto).collect(Collectors.toList());
////    }
//
//    public List<UserResponseDto> findUserWithPagination(int pageNumber, int pageSize) {
//        Page<User> page = userService.findPaginated(pageNumber, pageSize);
//        return page.stream().map(this::convertToUserResponseDto).collect(Collectors.toList());
//
//    }
}
