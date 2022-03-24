package com.revature.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// Spring creates basic CRUD
	
	Optional<User> findByUsername(String username);
	
	List<User> findByOrderByLastName(); // returns all users sorted by last name
	
	@Query("FROM User WHERE email LIKE %:pattern")
	List<User> findByEmailContains(String pattern); // finds email based on substring (johnsm -> johnsmith@email.com)

	
}
