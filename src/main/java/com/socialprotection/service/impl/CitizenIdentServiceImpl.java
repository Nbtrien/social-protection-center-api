package com.socialprotection.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialprotection.repository.CitizenIdentificationRepository;
import com.socialprotection.service.CitizenIdentificationService;
@Service
public class CitizenIdentServiceImpl implements CitizenIdentificationService{
	
	@Autowired
	private CitizenIdentificationRepository citizenIdentificationRepository;
}
