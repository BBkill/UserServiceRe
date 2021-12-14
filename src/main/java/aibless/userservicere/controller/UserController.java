package aibless.userservicere.controller;


import aibless.userservicere.dto.UserResponseDto;
import aibless.userservicere.mapper.UserMapper;

import aibless.userservicere.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    private final UserMapper userMapper;

/*
    //put user which is not in db
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User users = userService.createUser(user);
        return new ResponseEntity<User>(users, HttpStatus.OK);
    }

    //get user by id
    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //get user by email
    @GetMapping("/user?email={email}")
    public ResponseEntity<User> getUser(@PathVariable("email") String email) {
        User user = userService.getUser(email);
        return ResponseEntity.ok(user);
    }

    //get all user in db
    @GetMapping
    public ResponseEntity<List<User>> getUser() {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }

    //delete user which has email in db
    @DeleteMapping("/user?email={email}")
    public ResponseEntity<User> deleteUser(@PathVariable("email") String email) {
        User user = userService.getUser(email);
        return ResponseEntity.ok(userService.deleteUser(user));
    }

    //update user :v
    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

 */

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Integer id ) {
        return ResponseEntity.ok(userMapper.findUserById(id));
    }
}

