package com.huannguyen.vietsound;

import com.huannguyen.vietsound.entity.Album;
import com.huannguyen.vietsound.entity.Role;
import com.huannguyen.vietsound.entity.User;
import com.huannguyen.vietsound.service.RoleService;
import com.huannguyen.vietsound.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class VietsoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(VietsoundApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userService, RoleService roleService){
//		return args -> {
//			roleService.saveRole(new Role(null,"ROLE_USER",new ArrayList<>()));
//			roleService.saveRole(new Role(null,"ROLE_ADMIN",new ArrayList<>()));
//			userService.saveUser(new User(null,"huan01","12345678","check1@gmail.com",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
//			userService.saveUser(new User(null,"huan02","12345678","check2@gmail.com",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
//			userService.saveUser(new User(null,"huan03","12345678","check3@gmail.com",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
//			userService.saveUser(new User(null,"huan04","12345678","check4@gmail.com",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
//			userService.addToUser("huan01","ROLE_USER");
//			userService.addToUser("huan01","ROLE_ADMIN");
//			userService.addToUser("huan02","ROLE_USER");
//			userService.addToUser("huan03","ROLE_USER");
//			userService.addToUser("huan04","ROLE_USER");
//			userService.addToUser("huan02","ROLE_ADMIN");
//		};
//	}
}
