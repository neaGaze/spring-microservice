package com.stargate.transferfund.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.TransferRequest;

@Repository
public interface BankRepository extends CrudRepository<Bank, Integer> {
	
	List<Bank> findAll();

	@Query(value="UPDATE Bank b SET b.availableBalance = b.availableBalance - ?2 where b.bankId = ?1")
	@Modifying 
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	int debitBankBalance( Integer bankId, Double amount);
	

	@Query(value="UPDATE Bank b SET b.availableBalance = b.availableBalance + ?2 where b.bankId = ?1")
	@Modifying 
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	int creditBankBalance(Integer bankId,  Double amount);
}
