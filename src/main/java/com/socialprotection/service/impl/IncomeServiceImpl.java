package com.socialprotection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialprotection.entity.Income;
import com.socialprotection.repository.IncomeRepository;
import com.socialprotection.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	public List<Income> getList() {
		return incomeRepository.findAll();
	}

	@Override
	public Income getIncome(Long id) {
		return incomeRepository.findOne(id);
	}

	@Override
	public void save(Income income) {
		incomeRepository.save(income);
	}

	@Override
	public void delete(Long id) {
		incomeRepository.delete(id);
	}
}
