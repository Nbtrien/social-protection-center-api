package com.socialprotection.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialprotection.entity.Budget;
import com.socialprotection.repository.BudgetRepository;
import com.socialprotection.repository.ExpenseRepository;
import com.socialprotection.repository.IncomeRepository;
import com.socialprotection.service.BudgetService;

@Service
public class BudgetServiceImpl implements BudgetService{
	
	@Autowired
	private BudgetRepository budgetRepository;
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public List<Budget> getList() {
		return budgetRepository.findAll();
	}
	
	public Budget getBudget(Long id) {
		return budgetRepository.findOne(id);
	}
	
	public void save(Budget budget) {
		budgetRepository.save(budget);
    }
	
	public void delete(Long id) {
		budgetRepository.delete(id);
    }
	
}
