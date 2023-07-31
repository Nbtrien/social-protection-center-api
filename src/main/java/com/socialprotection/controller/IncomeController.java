package com.socialprotection.controller;

import java.util.List;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialprotection.entity.Budget;
import com.socialprotection.entity.Income;
import com.socialprotection.service.IncomeService;

@CrossOrigin
@RestController
@RequestMapping("/incomes")
public class IncomeController {

	@Autowired
	private IncomeService incomeservice;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Income>> listIncome() {
		List<Income> listIncome = incomeservice.getList();
		return ResponseEntity.ok(listIncome);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Income> getIncome(@PathVariable("id") Long id) {
		Income itemIn = incomeservice.getIncome(id);
		return ResponseEntity.ok(itemIn);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Income> addBudget(@RequestBody Income income) {
		try {
			Income _income = new Income(income.getBudget(), income.getBankAccount(), income.getIncomeName(), income.getIncomeDescription(), income.getAmount(),
					income.getDateTime());
			incomeservice.save(_income);
			return ResponseEntity.ok(_income);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(null);
		}
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Income> updateTutorial(@PathVariable("id") long id, @RequestBody Income income) {
		Income itemIn = incomeservice.getIncome(id);
		itemIn.setBudget(income.getBudget());
		itemIn.setBankAccount(income.getBankAccount());
		itemIn.setIncomeName(income.getIncomeName());
		itemIn.setIncomeDescription(income.getIncomeDescription());
		itemIn.setAmount(income.getAmount());
		itemIn.setDateTime(income.getDateTime());
		return ResponseEntity.ok(itemIn);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTutorial(@PathVariable("id") Long id) {
		try {
			incomeservice.delete(id);
			return ResponseEntity.ok("Delete Success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(null);
		}
	}
}
