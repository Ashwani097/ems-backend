package com.as.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.config.JwtProvider;
import com.as.models.User;
import com.as.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		
		User savedUser=userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		 
		
		
		throw new Exception("user not exists with userid "+userId);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws Exception {
		// TODO Auto-generated method stub
		User reqUser=findUserById(reqUserId);
		User user2=findUserById(userId2);
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());		
		userRepository.save(reqUser);
		userRepository.save(user2);
		return reqUser;
	}

	@Override
	public User updateUser(User user,Integer userId) throws Exception {
Optional<User> user1=userRepository.findById(userId);
		
		if(user1.isEmpty()) {
			throw new Exception("User not exsit with id "+userId);
			
		}
		User oldUser=user1.get();
		
		
//		User user1=new User(1,"code","as","email@email.com","12345");
//		
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender()!=null) {
			oldUser.setGender(user.getGender());
		}
		
		User updatedUser=userRepository.save(oldUser);
		return updatedUser;
		
	}

	@Override
	public List<User> searchUser(String query) {
		// TODO Auto-generated method stub
		
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		// TODO Auto-generated method stub
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		
		User user=userRepository.findByEmail(email);
		return user;
	}

}
