package net.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.boot.domain.User;
import net.boot.domain.userRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	
	@Autowired
	private userRepository userRepository;
	
	@GetMapping("/{id}")
	public User show(@PathVariable Long id) {
		return userRepository.findById(id).get();
		
	}

}
