package com.as.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.models.Reels;
import com.as.models.User;
import com.as.repository.ReelsRepository;


@Service
public class ReelsServiceImplementation implements ReelsService{
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReelsRepository reelsRepository;
	
	@Override
	public Reels createReels(Reels reel, User user) {
		// TODO Auto-generated method stub
		Reels createReels=new Reels();
		
		createReels.setTitle(reel.getTitle());
		createReels.setUser(user);
		createReels.setVideo(reel.getVideo());
		
		return reelsRepository.save(createReels);
		
//		return null;
	}

	@Override
	public List<Reels> findAllreels() {
		// TODO Auto-generated method stub
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserreel(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		 
		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
		
		
		
		
		
//		return null;
	}
	
	
	
	
	

}
