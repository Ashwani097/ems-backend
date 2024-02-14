package com.as.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.as.models.Reels;
import com.as.models.User;
import com.as.service.ReelsService;
import com.as.service.UserService;

@RestController
public class ReelsController { 
	
	
	@Autowired 
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel,@RequestHeader("Authorization")String jwt) {
		
		
		User reqUser=userService.findUserByJwt(jwt);
		
		Reels createdReels=reelsService.createReels(reel, reqUser);
		
		return createdReels;
		
	}
	
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels(){
		
		List<Reels> reels=reelsService.findAllreels();
		return reels;
	}
	
	
	@GetMapping("/api/reels/user")
	public List<Reels> finduserReels(@RequestHeader("Authorization")String jwt) throws Exception{
		
		
		User reqUser=userService.findUserByJwt(jwt);
		List<Reels> reels=reelsService.findUserreel(reqUser.getId());
		
		return reels;
		
	}
	
	
	

}
