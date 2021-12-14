package aibless.userservicere.service.iml;

import aibless.userservicere.model.User;
import aibless.userservicere.repository.UserRepository;
import aibless.userservicere.service.UserService;
import aibless.userservicere.exception.UserAlreadyExited;
import aibless.userservicere.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) throws UserAlreadyExited {
        User userByEmail = userRepository.findUserByEmail(user.getEmail()).orElse(null);
        if(userByEmail != null) {
            throw new UserAlreadyExited();
        }
        else {
            userRepository.save(user);
            return userRepository.findUserByEmail(user.getEmail()).orElse(null);
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> list = userRepository.findAll();
        if(list.size() != 0 ) {
            return list;
        }
        else {
            throw new UserNotFoundException();
        }

    }

    @Override
    public User getUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            return user;
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getUser(String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        if (user != null) {
            return user;
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User updateUser(User user) {
        User oldUser = userRepository.findUserByEmail(user.getEmail()).orElse(null);
        if (oldUser != null) {
            userRepository.delete(oldUser);
            userRepository.save(user);
            return userRepository.findUserByEmail(user.getEmail()).orElse(null);
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User deleteUser(User user) {
        User oldUser = userRepository.findUserByEmail(user.getEmail()).orElse(null);
        if (oldUser != null) {
            userRepository.delete(user);
            return user;
        }
        else {
            throw new UserNotFoundException();
        }
    }

}
