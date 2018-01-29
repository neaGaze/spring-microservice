package stargate.application.service;
import stargate.application.exception.*;
import stargate.application.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
