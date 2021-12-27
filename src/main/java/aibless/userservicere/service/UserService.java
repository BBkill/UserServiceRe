package aibless.userservicere.service;

import aibless.userservicere.dto.UserRequestDto;
import aibless.userservicere.dto.UserResponseDto;
import aibless.userservicere.model.paging.PagingResponse;
import aibless.userservicere.repository.UserRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto user);

    List<UserResponseDto> getUsers();

    UserResponseDto getUser(int id);

    UserResponseDto getUser(String email);

    UserResponseDto deleteUser(String email );

    UserResponseDto updateUser(UserRequestDto user);

    PagingResponse findPaginated(int pageNumber, int pageSize);

}
