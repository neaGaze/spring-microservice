package com.stargate.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.stargate.entity.*;;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GetBalanceRepository extends CrudRepository<Balance, Double> {
	@Query("select b.available_balance from bank b")
    List<Balance> findBalance();
}





//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
////import javax.persistence.TypedQuery;
//import org.springframework.stereotype.Repository;

//@Repository
//public class GetBalanceRepository {
//	@PersistenceContext
//	private EntityManager em;
//	
//	public int findBalance(String id) {
//		return em.find(balance, id);
//	}
//}
