package com.socialprotection.service;

import java.util.List;

import com.socialprotection.entity.BankAccount;

public interface BankAccountService {

public List<BankAccount> getList();
	
	public BankAccount getBankAccount(Long id);
	
	public void save(BankAccount bankAccount);
	
	public void delete(Long id);
}
