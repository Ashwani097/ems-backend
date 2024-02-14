package com.as.service;

import java.util.List;

import com.as.models.Reels;
import com.as.models.User;

public interface ReelsService {
	
	public Reels createReels(Reels reel,User user);
	
	public List<Reels> findAllreels();
	
	
	public List<Reels> findUserreel(Integer userId) throws Exception;
	
	

}
