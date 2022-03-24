package com.revature.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.AddressRepository;
import com.revature.data.UserRepository;
import com.revature.exception.UserNotFoundException;
import com.revature.model.User;

@Service
public class UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addressRepo;
	
	@Transactional(readOnly=true) // make sure method fires against database in one unit
	public Set<User> findAll() {
		
		return userRepo.findAll().stream()
				.collect(Collectors.toSet());
	}
	
	 
	@Transactional(propagation=Propagation.REQUIRES_NEW) // when method is invoked, it begins a *new* transaction (one unit of work)
	public User add(User u) {
		
		// Check if user has an Address or Addresses
		if (u.getAddresses() != null) {
			u.getAddresses().forEach(address -> addressRepo.save(address));
		}
		
		return userRepo.save(u);
	}
	
	@Transactional(propagation=Propagation.REQUIRED) // default setting
	public void remove(int id) {
		userRepo.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("No user found with username " + username));
	}
	
	@Transactional(readOnly=true)
	public User getById(int id) {
		if (id <= 0) {
			log.warn("ID cannot be <= 0. ID passed was: {}", id);
			return null;
		} else {
			return userRepo.getById(id);
		}
	}
}
