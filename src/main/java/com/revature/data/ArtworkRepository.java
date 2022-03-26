package com.revature.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Artwork;

@Repository // stereotype annotation indicating that this is a component responsible for persisting Artwork objects
public interface ArtworkRepository extends JpaRepository<Artwork, Integer>{
	
	Set<Artwork> findByOwnersUsername(String username);

}