package aibless.userservicere.service.iml;

import aibless.userservicere.dto.UserRequestDto;
import aibless.userservicere.dto.UserResponseDto;
import aibless.userservicere.exception.*;
import aibless.userservicere.model.Role;
import aibless.userservicere.model.User;
import aibless.userservicere.model.paging.PagingResponse;
import aibless.userservicere.repository.RoleRepository;
import aibless.userservicere.repository.UserRepository;
import aibless.userservicere.security.service.JwtProvider;
import aibless.userservicere.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceIml implements UserService{


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private final JwtProvider jwtProvider;

    @Override
    public String doLogin(String email, String passWord) {
        User user = userRepository.findUserByEmail(email).get();
        if (passWord.equals(user.getPassWord())){
            return jwtProvider.createToken(email, user.getRoles().iterator().next().getName());
        } else {
            throw new UserNamePassWordException();
        }
    }

    @Override
    public UserResponseDto createUser(UserRequestDto user) {

        User user1 = userRepository.findUserByEmail(user.getEmail()).orElse(null);
        if(user1 != null) {
            throw new UserAlreadyExited();
        }
        else if(userRepository.findUserByPhoneNumber(user.getPhoneNumber()).orElse(null) != null) {
            throw new UserPhoneNumberAlreadyExited();
        }
        else {
            return modelMapper.map( userRepository.findUserByEmail(user.getEmail()),UserResponseDto.class);
        }
    }

    @Override
    public List<UserResponseDto> getUsers() {
//        log.info("running");
//        log.error("get list users error");
        List<UserResponseDto> list = userRepository.findAll().stream().map(user ->modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public UserResponseDto getUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        Role role = roleRepository.findById(user.getId()).orElse(null);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        log.info(roles.toString());
        user.setRoles(roles);
        if (user != null) {
            return modelMapper.map( user, UserResponseDto.class);
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public UserResponseDto getUser(String email) {
        UserResponseDto user = modelMapper.map(userRepository.findUserByEmail(email).orElse(null), UserResponseDto.class);
        if (user != null) {
            return user;
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto user) {
        User oldUser = userRepository.findUserByEmail(user.getEmail()).orElse(null);
        if (oldUser != null) {
            userRepository.delete(oldUser);
            userRepository.save(modelMapper.map(user, User.class));
            return modelMapper.map(userRepository.findUserByEmail(user.getEmail()), UserResponseDto.class);
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public PagingResponse findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<User> page = userRepository.findAll(pageable);
        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setUsers(page.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList()));
        pagingResponse.setPageNo(pageNumber);
        pagingResponse.setPageSize(pageSize);
        pagingResponse.setTotal(pagingResponse.getUsers().size());
        return pagingResponse;
    }

    @Override
    public UserResponseDto deleteUser(String email) {
        User oldUser = userRepository.findUserByEmail(email).orElse(null);
        if (oldUser != null) {
            userRepository.delete(oldUser);
            return modelMapper.map(oldUser, UserResponseDto.class);
        }
        else {
            throw new UserNotFoundException();
        }
    }

}
