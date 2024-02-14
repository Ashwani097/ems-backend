package com.as.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.models.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer>{

}
