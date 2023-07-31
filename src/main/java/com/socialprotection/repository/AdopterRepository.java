package com.socialprotection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialprotection.entity.Adopter;

public interface AdopterRepository extends JpaRepository<Adopter, Long>{
	
	List<Adopter> findByEmailContaining(String Title);

}
