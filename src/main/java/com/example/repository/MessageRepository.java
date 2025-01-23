package com.example.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Transactional
    @Query("DELETE FROM Message m WHERE m.messageId = ?1")
    int deleteMessageById(Integer id);

    @Transactional
    @Query("UPDATE Message m SET m.messageText = ?1 WHERE m.messageId = ?2")
    int updateMessageText(String messageText, Integer messageId);

    @Query("SELECT m FROM Message m WHERE m.postedBy = ?1")
    List<Message> getAccountMessages(Integer accountId);

}
