package com.socialprotection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialprotection.entity.Adoption;
import com.socialprotection.repository.AdoptionRepository;
import com.socialprotection.service.AdoptionService;
import com.socialprotection.utils.StatusResponse;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdoptionController {

	@Autowired
	private AdoptionService adoptionService;

//	@PostMapping(value = "/adoptions", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<StatusResponse> saveAdoption(Adoption adoption){
//		return ResponseEntity.ok(new StatusResponse(true, "Oker"));
//		
//	}

	// create new adoption
	@PostMapping(value = "/adoption", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Adoption> createAdoption(@RequestBody Adoption adoption) {
//		return adoptionRepository.save(adoption);
		return ResponseEntity.ok(adoptionService.saveAdoption(adoption));
	}

	@GetMapping(value = "/getadoption")
	public ResponseEntity<List<Adoption>> getall() {
		return ResponseEntity.ok(adoptionService.getAdoption());
	}

}
