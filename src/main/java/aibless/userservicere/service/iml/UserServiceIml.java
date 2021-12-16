package aibless.userservicere.service.iml;

import aibless.userservicere.exception.UserPhoneNumberAlreadyExited;
import aibless.userservicere.model.User;
import aibless.userservicere.repository.UserRepository;
import aibless.userservicere.service.UserService;
import aibless.userservicere.exception.UserAlreadyExited;
import aibless.userservicere.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIml implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) throws UserAlreadyExited {
        User user1 = userRepository.findUserByEmail(user.getEmail()).orElse(null);
        if(user1 != null) {
            throw new UserAlreadyExited();
        }
        else if(userRepository.findUserByPhoneNumber(user.getPhoneNumber()).orElse(null) != null) {
            throw new UserPhoneNumberAlreadyExited();
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
    public Page<User> findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return userRepository.findAll(pageable);
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
