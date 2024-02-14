package com.as.service;

import java.util.List;

import com.as.models.Chat;
import com.as.models.Message;
import com.as.models.User;

public interface MessageService {
	
	public Message createMessage(User user,Integer chatId,Message req) throws Exception;
	
	public List<Message> findChatMessages(Integer chatId) throws Exception;

}
