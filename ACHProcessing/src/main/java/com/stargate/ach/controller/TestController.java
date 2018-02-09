package com.stargate.ach.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping(value="/testSuite", method=RequestMethod.GET)
	public ResponseEntity<Integer> getTransferDetails() {
	    return new ResponseEntity<Integer>(360, HttpStatus.ACCEPTED);
    }
}
