package com.stargate.controller;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.stargate.repository.*;
import com.stargate.entity.*;
@Controller
@EnableAutoConfiguration
@RequestMapping("/demo")
public class GetBalanceController {
	@Autowired
	GetBalanceRepository repo;

	@GetMapping(path="/find")
	public @ResponseBody List<Balance> findBalance() {
		// This returns a JSON or XML with the users
		return repo.findBalance();
	}
    
}



//@RequestMapping(method = RequestMethod.GET, value = "{id}")
//@ResponseBody()
//public int findBalance(@PathVariable("id") String custID) {
//	return service.findBalance(custID);
//}
