package aibless.userservicere.controller;


import org.springframework.validation.annotation.Validated;

import aibless.userservicere.model.User;
import aibless.userservicere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//http://localhost:8080/api/v1/userinfo
@RestController
@RequestMapping("/api/v1/userinfo")
public class UserController {
    @Autowired
    private UserService userService;


    //put user which is not in db
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
        User users = userService.createUser(user);
        return new ResponseEntity<User>(users, HttpStatus.OK);
    }


    //get user by id
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id)
    {
        User user = userService.getUser(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    //get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUser(@PathVariable("email") String email)
    {
        User user = userService.getUser(email);
        return ResponseEntity.ok(user);
    }

    //get all user in db
    @GetMapping
    public ResponseEntity<List<User>> getUser()
    {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }


    //delete user which has email in db
    @DeleteMapping(path = "/email/{email}")
    public ResponseEntity<User> deleteUser(@PathVariable("email") String email)
    {
        User user = userService.getUser(email);
        return ResponseEntity.ok(userService.deleteUser(user));
    }

    //update user :v
    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user)
    {
        return ResponseEntity.ok(userService.updateUser(user));
    }

}

