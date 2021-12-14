package aibless.userservicere.service;

import aibless.userservicere.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUser();

    User getUser(int id);

    User getUser(String email);

    User deleteUser(User user);

    User updateUser(User user);

}
