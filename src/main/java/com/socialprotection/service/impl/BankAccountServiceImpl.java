package com.socialprotection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialprotection.entity.BankAccount;
import com.socialprotection.repository.BankAccountRepository;
import com.socialprotection.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository backAccountRepository;
	
	@Override
	public List<BankAccount> getList() {
		return backAccountRepository.findAll();
	}

	@Override
	public BankAccount getBankAccount(Long id) {
		return backAccountRepository.findOne(id);
	}

	@Override
	public void save(BankAccount backAccount) {
		backAccountRepository.save(backAccount);
	}

	@Override
	public void delete(Long id) {
		backAccountRepository.delete(id);
	}
}
