package com.stargate.ach.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ResponseEntity<Integer> getTransferDetails() {
	    return new ResponseEntity<Integer>(360, HttpStatus.ACCEPTED);
    }
}
