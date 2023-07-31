package com.socialprotection.service;

import java.util.List;

import com.socialprotection.entity.Adoption;

public interface AdoptionService {
	
	public Adoption saveAdoption(Adoption adoption);
	
	public List<Adoption> getAdoption();

}
