package com.stargate.status.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stargate.status.entity.Transaction;
@Repository
public interface GetStatusRepository extends CrudRepository<Transaction,String>  {

}
