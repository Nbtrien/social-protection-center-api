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

import com.socialprotection.entity.BankAccount;
import com.socialprotection.service.BankAccountService;

@CrossOrigin
@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

	@Autowired
	private BankAccountService bankaccountservice;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BankAccount>> listBankAccount() {
		List<BankAccount> bankAccounts = bankaccountservice.getList();
		return ResponseEntity.ok(bankAccounts);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BankAccount> getBankAccount(@PathVariable("id") Long id) {
		BankAccount bankAccount = bankaccountservice.getBankAccount(id);
		return ResponseEntity.ok(bankAccount);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BankAccount> addBudget(@RequestBody BankAccount bankAccount) {
		try {
			BankAccount _bankAccount = new BankAccount(bankAccount.getAccountNumber(), bankAccount.getAccountName(),
					bankAccount.getBankName(), bankAccount.getBalance());
			bankaccountservice.save(_bankAccount);
			return ResponseEntity.ok(_bankAccount);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(null);
		}
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BankAccount> updateTutorial(@PathVariable("id") long id, @RequestBody BankAccount bankAccount) {
		BankAccount itemBank = bankaccountservice.getBankAccount(id);
		itemBank.setAccountNumber(bankAccount.getAccountNumber());
		itemBank.setAccountName(bankAccount.getAccountName());
		itemBank.setBankName(bankAccount.getBankName());
		itemBank.setBalance(bankAccount.getBalance());
		bankaccountservice.save(itemBank);
		return ResponseEntity.ok(itemBank);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTutorial(@PathVariable("id") Long id) {
		try {
			bankaccountservice.delete(id);
			return ResponseEntity.ok("Delete Success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(null);
		}
	}
}
