package com.lvb.oauth2.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvb.oauth2.entity.Account;
import com.lvb.oauth2.service.AccountService;

@RestController
@RequestMapping("/secured-end-points")
public class MyEndPoints {
@Autowired
AccountService ac;

	@RequestMapping("/accounts")
	public ResponseEntity<List<Account>> getAccounts(){
		try{
			return new ResponseEntity(ac.getAccount(),HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity(ac.getAccount(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
