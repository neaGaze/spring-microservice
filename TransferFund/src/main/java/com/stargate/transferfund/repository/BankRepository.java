package com.stargate.transferfund.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.stargate.transferfund.entity.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank, Integer> {
	
	//@Query(value="select b from Bank b")
	List<Bank> findAll();
}
