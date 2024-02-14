package com.as.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as.Response.AuthResponse;
import com.as.config.JwtProvider;
import com.as.models.User;
import com.as.repository.UserRepository;
import com.as.request.LoginRequest;
import com.as.service.CustomUserDetailsService;
import com.as.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customuserDetails;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist=userRepository.findByEmail(user.getEmail());
		
		if(isExist!=null) {
			throw new Exception("Email Already used with another account");
		}
		
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser=userRepository.save(newUser);
		
		
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		
		
		String token=JwtProvider.generateToken(authentication);
		
		AuthResponse res=new AuthResponse(token,"Register Sucess");
		
		return res;
	}
	
	//auth/signin
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest LoginRequest) {
		
		Authentication authentication=authenticate(LoginRequest.getEmail(),LoginRequest.getPassword());
		
		String token=JwtProvider.generateToken(authentication);
		
		AuthResponse res=new AuthResponse(token,"Login Sucess");
		
		return res;
	}


	private Authentication authenticate(String email, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails= customuserDetails.loadUserByUsername(email);
		
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid USername");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not match");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		
//		return null;
	}
}







