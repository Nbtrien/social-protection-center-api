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
import com.socialprotection.service.BudgetService;

@CrossOrigin
@RestController
@RequestMapping("/budgets")
public class BudgetController {

	@Autowired
	private BudgetService budgetservice;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Budget>> listBudget() {
		List<Budget> listEmp = budgetservice.getList();
		return ResponseEntity.ok(listEmp);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Budget> getBudget(@PathVariable("id") Long id) {
		Budget itemBud = budgetservice.getBudget(id);
		return ResponseEntity.ok(itemBud);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Budget> addBudget(@RequestBody Budget budget) {
		try {
			Budget _budget = new Budget(budget.getBudgetName(), budget.getBudgetDescription(), budget.getAmout(),
					budget.getStartDate(), budget.getEndDate());
			budgetservice.save(_budget);
			return ResponseEntity.ok(_budget);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(null);
		}
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Budget> updateTutorial(@PathVariable("id") long id, @RequestBody Budget budget) {
		Budget itemBud = budgetservice.getBudget(id);
		itemBud.setBudgetName(budget.getBudgetName());
		itemBud.setBudgetDescription(budget.getBudgetDescription());
		itemBud.setAmout(budget.getAmout());
		itemBud.setStartDate(budget.getStartDate());
		itemBud.setEndDate(budget.getEndDate());
		budgetservice.save(itemBud);
		return ResponseEntity.ok(itemBud);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTutorial(@PathVariable("id") Long id) {
		try {
			budgetservice.delete(id);
			return ResponseEntity.ok("Delete Success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(null);
		}
	}

}
