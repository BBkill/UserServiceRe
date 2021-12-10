package aibless.userservicere.repository;

import aibless.userservicere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserById(Integer integer);
    Optional<User> findUserByEmail(String email);
}
