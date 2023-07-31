package com.socialprotection.service;

import java.util.List;

import com.socialprotection.entity.Budget;

public interface BudgetService {
	
	public List<Budget> getList();
	
	public Budget getBudget(Long id);
	
	public void save(Budget budget);
	
	public void delete(Long id);
}