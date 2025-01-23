package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message m) throws Exception {
        checkMessageValidity(m.getMessageText());
        return messageRepository.save(m);
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer id) throws Exception {
        return messageRepository.findById(id).orElseThrow();
    }

    public int deleteMessageById(Integer id) throws Exception {
        int n = messageRepository.deleteMessageById(id);
        if(n==0) throw new Exception();
        return n;
    }

    public int updateMessageText(Integer messageId, Message m) throws Exception {
        String newMsgText = m.getMessageText();
        checkMessageValidity(newMsgText);
        int n = messageRepository.updateMessageText(newMsgText, messageId);
        if(n == 0) throw new Exception();
        return n;
    }

    public List<Message> getAccountMessages(Integer accountId) {
        return this.messageRepository.getAccountMessages(accountId);
    }

    private static void checkMessageValidity(String msg) throws Exception {
        if(msg.isBlank() || msg.length() > 255) {
            throw new Exception();
        }
    }

}
