package com.as.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.models.Message;

public interface MessageRepository extends JpaRepository<Message,Integer>{
	
	public List<Message> findByChatId(Integer chatId);

}
