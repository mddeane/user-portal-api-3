package com.revature.web;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

@RestController
@RequestMapping("/users") // available at http://localhost:5000/api/users
@CrossOrigin(origins="*", allowedHeaders="*")
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	// clients sends a GET request to http://localhost:5000/api/users and retrieves all users
	@GetMapping
	public ResponseEntity<Set<User>> getAll() {
		// ResponseEntity allows you to edit parts of the HTTP response like the status
		return ResponseEntity.ok(userServ.findAll());
	}
	
	// ad user -> accept POST request 
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User u) {
										// @Valid enforces Validations set up
		return ResponseEntity.ok(userServ.add(u));
		// AOP intercepts invalid response with ResponseEntity Handler
	}
	
	// Find user by their id
	@GetMapping("/{id}") // Allows client to send request to http://localhost:5000/api/users/{id}
	public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
		return ResponseEntity.ok(userServ.getById(id));
	}

	// Find user by their username
	@GetMapping("find/{username}") // Allows client to send request to http://localhost:5000/api/users/{username}
	public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
		return ResponseEntity.ok(userServ.getUserByUsername(username));
	}

	// Delete/remove user by id
	@DeleteMapping("/{id}") // 
	public void removeUser(@PathVariable("id") int id) {
			userServ.remove(id);
	}
}
