package aibless.userservicere.service.iml;

import aibless.userservicere.dto.UserRequestDto;
import aibless.userservicere.dto.UserResponseDto;
import aibless.userservicere.exception.UserPhoneNumberAlreadyExited;
import aibless.userservicere.model.User;
import aibless.userservicere.model.paging.PagingResponse;
import aibless.userservicere.repository.UserRepository;
import aibless.userservicere.service.UserService;
import aibless.userservicere.exception.UserAlreadyExited;
import aibless.userservicere.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIml implements UserService{

    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

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
        List<UserResponseDto> list = userRepository.findAll().stream().map(user ->modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public UserResponseDto getUser(int id) {
        UserResponseDto user = modelMapper.map( userRepository.findById(id).orElse(null), UserResponseDto.class);
        if(user != null) {
            return user;
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
