package aibless.userservicere.controller;


import aibless.userservicere.dto.UserRequestDto;
import aibless.userservicere.dto.UserResponseDto;
import aibless.userservicere.model.paging.PagingResponse;
import aibless.userservicere.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")

public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Integer id ) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> getUser(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.getUser(email));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUser() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @DeleteMapping("/user={email}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.deleteUser(email));
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @GetMapping("/page")
    public ResponseEntity<PagingResponse> findPaginated(@RequestParam("pageNumber") Integer pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize ) {
        return ResponseEntity.ok(userService.findPaginated(pageNumber, pageSize));
    }
}

