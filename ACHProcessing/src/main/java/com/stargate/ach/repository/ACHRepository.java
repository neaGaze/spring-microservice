package com.stargate.ach.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.stargate.ach.entity.ACHTransaction;

@Repository
public interface ACHRepository extends CrudRepository <ACHTransaction, Integer> {
	@Modifying
	@Transactional
	@Query("UPDATE ACHTransaction t SET t.status = ?2 WHERE t.transaction_id= ?1")
	public Integer updateStatusInDB(Integer id, String updatedStatus);
}
