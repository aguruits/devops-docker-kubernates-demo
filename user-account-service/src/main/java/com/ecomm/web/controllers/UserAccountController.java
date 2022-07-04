package com.ecomm.web.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.entities.UserAccount;
import com.ecomm.repositories.UserAccountRepository;

@RestController
@RequestMapping("/user")
public class UserAccountController {
	
	private final UserAccountRepository userAccountRepository;
    
	Logger log = LogManager.getLogger(UserAccountController.class);
    
    @Autowired
    public UserAccountController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
    
	/*
	 * @GetMapping("/{userIdStr}") public ResponseEntity<UserAccount>
	 * findUserbyStringId(@PathVariable("userIdStr") String userIdStr) {
	 * log.info("Finding user account for user id: "+ userIdStr); Long userId =
	 * Long.valueOf(userIdStr);
	 * 
	 * UserAccount userAccount = userAccountRepository.findByUserId(userId);
	 * if(userAccount != null) { return new ResponseEntity<UserAccount>(userAccount,
	 * HttpStatus.OK); } else { return new
	 * ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND); } }
	 */

    @GetMapping("/{userId}")
    public ResponseEntity<UserAccount> findUserbyId(@PathVariable("userId") Long userId) {
        log.info("Finding user account for user id: "+userId);
        
        Optional<UserAccount> userAccountOpt = userAccountRepository.findByUserId(userId);
        
        if(userAccountOpt.isPresent()) {
        	UserAccount userAccount = userAccountOpt.get();
        	return new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
        } else {
	        return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
	    }
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<UserAccount>> getUserList() {
        log.info("Finding all users list ");
        List<UserAccount> inventoryItems = userAccountRepository.findAll();
        return new ResponseEntity<List<UserAccount>>(inventoryItems, HttpStatus.OK);
    }
    
}
