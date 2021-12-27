package aibless.userservicere.config;

import aibless.userservicere.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public UserMapper usersMapper() {
        return new UserMapper();
    }

}
