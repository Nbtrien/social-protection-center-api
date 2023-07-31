package com.socialprotection.service;

import java.util.List;

import com.socialprotection.entity.Income;

public interface IncomeService {

	public List<Income> getList();

	public Income getIncome(Long id);

	public void save(Income income);

	public void delete(Long id);
}
