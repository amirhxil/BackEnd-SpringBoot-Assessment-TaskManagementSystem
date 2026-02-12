package com.example.taskmanager;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword("admin123"); // simple password for demo
				admin.setRole(com.example.taskmanager.entity.Role.ADMIN);
				userRepository.save(admin);


				User user1 = new User();
				user1.setUsername("user1");
				user1.setPassword("user123"); // simple password for demo
				user1.setRole(com.example.taskmanager.entity.Role.USER);
				userRepository.save(user1);
				System.out.println("User1 user created: username=user1, password=user123");
			}
		};
	}
}
