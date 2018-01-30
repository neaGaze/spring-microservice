package com.stargate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stargate.entity.Bank;
import com.stargate.repository.GetBalanceRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(path = "/getbalance")
public class GetBalanceController {

	@Autowired
	private GetBalanceRepository getBalanceRepository;

	
	@GetMapping(path="/find")
	public @ResponseBody Bank getBank (@RequestParam String account_no) {
		return getBalanceRepository.findBalance(account_no);
	
	}

	/*@GetMapping(path="/find/{account_no}")
	public @ResponseBody List<Bank> getBank (@PathVariable("account_no") String account_no) {
		return getBalanceRepository.findBalance(account_no);
	
	}*/
	
}