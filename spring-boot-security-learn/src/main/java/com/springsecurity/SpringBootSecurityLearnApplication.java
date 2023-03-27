package com.springsecurity;

import com.springsecurity.models.User;
import com.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication
public class SpringBootSecurityLearnApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {

		SpringApplication.run(SpringBootSecurityLearnApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1=new User();
		user1.setUsername("Roshni");
		user1.setPassword(this.bCryptPasswordEncoder.encode("durgesh"));
		user1.setEmail("roshni@gmail.com");
		user1.setRole("ROLE_ADMIN");
		userRepository.save(user1);

		User user2=new User();
		user2.setUsername("John");
		user2.setPassword(this.bCryptPasswordEncoder.encode("durgesh"));
		user2.setEmail("john@gmail.com");
		user2.setRole("ROLE_NORMAL");
		userRepository.save(user2);

	}
}
