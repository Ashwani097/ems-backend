package com.as.service;

import java.util.List;

import com.as.models.Chat;
import com.as.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser,User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);

}
