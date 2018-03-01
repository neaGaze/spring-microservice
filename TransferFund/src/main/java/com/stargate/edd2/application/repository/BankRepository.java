package com.stargate.edd2.application.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stargate.edd2.application.entity.Bank;


@Repository
public interface BankRepository extends CrudRepository<Bank, Integer> {

	@Query(value="UPDATE Bank b SET b.availableBalance = b.availableBalance - ?2 where b.accountNo = ?1 and b.availableBalance - ?2 > 0")
	@Modifying 
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	int debitBankBalance(String accountNo, Double amount);
	

	@Query(value="UPDATE Bank b SET b.availableBalance = b.availableBalance + ?2 where b.accountNo = ?1")
	@Modifying 
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	int creditBankBalance(String accountNo,  Double amount);
}

