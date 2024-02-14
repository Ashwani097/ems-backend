package com.as.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.as.models.Message;
import com.as.models.User;
import com.as.service.MessageService;
import com.as.service.UserService;

@RestController
public class CreateMessage {
	
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/messages/{chatId}")
	public Message createMessage(@RequestBody Message req,@RequestHeader("Authorization") String jwt
								,@PathVariable Integer chatId) throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		
		Message message=messageService.createMessage(user, chatId, req);
		
		return message;
	}
	
	@GetMapping("/api/messages/{chatId}")
	public List<Message> findChatMessage(@RequestHeader("Authorization") String jwt
								,@PathVariable Integer chatId) throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		
		List<Message> message=messageService.findChatMessages(chatId);
		
		return message;
	}

}
