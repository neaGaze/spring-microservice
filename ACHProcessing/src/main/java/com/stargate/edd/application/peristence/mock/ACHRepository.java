package com.stargate.edd.application.peristence.mock;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.stargate.edd.application.entity.ACHTransaction;

@Repository
public interface ACHRepository extends CrudRepository <ACHTransaction, String> {
	@Modifying
	@Transactional
	@Query("UPDATE ACHTransaction t SET t.status = ?2 WHERE t.id= ?1")
	public Integer updateStatusInDB(String id, String updatedStatus);
}
