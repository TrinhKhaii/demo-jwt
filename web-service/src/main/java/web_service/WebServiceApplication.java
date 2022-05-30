package web_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import web_service.model.Role;
import web_service.service.impl.AccountService;
import web_service.service.impl.RoleService;

@SpringBootApplication
public class WebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }


//    @Bean
//    CommandLineRunner run(RoleService roleService) {
//        return args -> {
//            roleService.save(new Role(null, "ROLE_ADMIN"));
//            roleService.save(new Role(null, "ROLE_SELLER"));
//            roleService.save(new Role(null, "ROLE_BUSSINESS_STAFF"));
//            roleService.save(new Role(null, "ROLE_STOREKEEPER"));
//        };
//    }
}
