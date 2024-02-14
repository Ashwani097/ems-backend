package com.as.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as.models.Chat;
import com.as.models.Message;
import com.as.models.User;
import com.as.repository.ChatRepository;
import com.as.repository.MessageRepository;
@Service
public class MessageServiceImplementation implements MessageService {

	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepository chatRepository;
	
	
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
		// TODO Auto-generated method stub
		
		Message message=new Message();
		
		Chat chat=chatService.findChatById(chatId);
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimeStamp(LocalDateTime.now());
		
		Message savedMessage=messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatMessages(Integer chatId) throws Exception {
		// TODO Auto-generated method stub
		Chat chat=chatService.findChatById(chatId);
		
		
		return messageRepository.findByChatId(chatId);
	}

}
