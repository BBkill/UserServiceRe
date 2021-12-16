package aibless.userservicere.controller;


import aibless.userservicere.dto.UserRequestDto;
import aibless.userservicere.dto.UserResponseDto;
import aibless.userservicere.mapper.UserMapper;
import aibless.userservicere.validator.constrain.ContactNumberConstraint;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")

public class UserController {

    private final UserMapper userMapper;

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Integer id ) {
        return ResponseEntity.ok(userMapper.findUserById(id));
    }

    @GetMapping("/user={email}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("email") String email) {
        return ResponseEntity.ok(userMapper.findUserByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUser() {
        return ResponseEntity.ok(userMapper.findUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userMapper.addUser(user));
    }

    @DeleteMapping("/user={email}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable("email") String email) {
        return ResponseEntity.ok(userMapper.deleteUser(email));
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userMapper.updateUser(user));
    }

    @GetMapping("/page/{pageNumber}/{pageSize}")
    public ResponseEntity<List<UserResponseDto>> findPaginated(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize ) {
        return ResponseEntity.ok(userMapper.findUserWithPagination(pageNumber, pageSize));
    }
}

