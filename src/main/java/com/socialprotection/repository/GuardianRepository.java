package com.socialprotection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialprotection.entity.Guardian;

public interface GuardianRepository extends JpaRepository<Guardian, Long>{

}
