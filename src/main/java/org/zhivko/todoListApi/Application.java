package org.zhivko.todoListApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.zhivko.todoListApi.entities.User;
import org.zhivko.todoListApi.servicesImpl.UserServiceImpl;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

    @Autowired
    private UserServiceImpl userService = null;

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init() {

        return (args) -> {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            User user = new User();

            user.setUsername("admin@admin.com");
            user.setPassword(encoder.encode("admin123"));
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);

            this.userService.make(user);

        };
    }
}
