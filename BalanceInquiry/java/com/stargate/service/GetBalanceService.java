package com.stargate.service;
import com.stargate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stargate.exception.*;

//@Service
//public class GetBalanceService {
//	@Autowired
//	private GetBalanceRepository repository;
//	public int findBalance(String custID) {
//		int bal = repository.findOne(custId);
//		if (bal == 0) {
//			throw new NoBalanceException("Low Balance");
//		}
//		return bal;
//	}
//
//}
