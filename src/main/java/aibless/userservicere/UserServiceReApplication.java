package aibless.userservicere;

import aibless.userservicere.mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceReApplication {

    @Bean
    public UserMapper usersMapper() {
        return new UserMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceReApplication.class, args);
    }

}
