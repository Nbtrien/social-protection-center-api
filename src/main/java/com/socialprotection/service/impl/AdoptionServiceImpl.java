package com.socialprotection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialprotection.entity.Adopter;
import com.socialprotection.entity.Adoption;
import com.socialprotection.repository.AdopterRepository;
import com.socialprotection.repository.AdoptionRepository;
import com.socialprotection.repository.CitizenIdentificationRepository;
import com.socialprotection.service.AdoptionService;

@Service
public class AdoptionServiceImpl implements AdoptionService{
	
	@Autowired
	private AdopterRepository adopterRepository;
	
	@Autowired
	private AdoptionRepository adoptionRepository;
	
	@Autowired
	private CitizenIdentificationRepository citizenIdentificationRepository;


	@Override
	public Adoption saveAdoption(Adoption adoption) {
		
//		adoption.getAdopters().forEach(pl -> pl.setAdoption(adoption)); 
		
		Adoption adoption2 = adoptionRepository.save(adoption);
		
		
//		for (Adopter adopter : adoption.getAdopters()) {
//			
//			citizenIdentificationRepository.save(adopter.getCitizenIdentification());
//			
//			adopterRepository.save(adopter);
//		}
		
		return adoption2;
	}


	@Override
	public List<Adoption> getAdoption() {
		// TODO Auto-generated method stub
		
		
		
		return adoptionRepository.findAll();
	}

}
