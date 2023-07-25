package com.socialprotection.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.socialprotection.entity.Guardian;
import com.socialprotection.repository.GuardianRepository;
import com.socialprotection.service.GuardianService;

public class GuardianServiceImpl implements GuardianService{
	@Autowired
	private GuardianRepository guardianRepository;

	@Override
	public Guardian save(Guardian guardian) {
		// TODO Auto-generated method stub
		return guardianRepository.save(guardian);
	}

}