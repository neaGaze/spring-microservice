package com.stargate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stargate.entity.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface GetBalanceRepository extends CrudRepository<Bank, Integer> {
	
	@Query(name="balance")
	Bank findBalance(@Param("account_no") String account_no);
	
	
	//List<Bank> findBalance();
	
	/*//@Query("select b.available_balance from bank b where b.account_no= :account_no")
	//String findOne(String account_no);

	List<Bank> findAll();*/
}
