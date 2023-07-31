package com.socialprotection.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialprotection.entity.Guardian;
import com.socialprotection.service.GuardianService;

@CrossOrigin
@RestController
@RequestMapping("/api/guardians")
public class GuardianController {
	
	@Autowired
	private GuardianService guardianService;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guardian> saveGuardian(@RequestBody Guardian guardian) {
		return ResponseEntity.ok(guardianService.save(guardian));
	}
}
