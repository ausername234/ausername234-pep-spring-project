package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@Controller
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }


    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account a) {

        try {
            Account registeredAccount = accountService.registerRequest(a);
            return ResponseEntity.ok(registeredAccount);
        } catch(DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account a) {
        try {
            accountService.loginRequest(a);
            return ResponseEntity.ok(a);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message m) {
        try {
            Message createdMsg = messageService.createMessage(m);
            return ResponseEntity.ok(createdMsg);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> messageGetById(@PathVariable Integer messageId) {
        try {
            return ResponseEntity.ok(messageService.getMessageById(messageId));
        } catch(Exception e) {
            return ResponseEntity.ok().body(null);
        }
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer messageId) {
        try {
            return ResponseEntity.ok(messageService.deleteMessageById(messageId));
        } catch(Exception e) {
            return ResponseEntity.ok().body(null);
        }
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageText(@PathVariable Integer messageId, @RequestBody Message m) {
        try {
            return ResponseEntity.ok(messageService.updateMessageText(messageId, m));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAccountMessages(@PathVariable Integer accountId) {
        try {
            return ResponseEntity.ok(messageService.getAccountMessages(accountId));
        } catch(Exception e) {
            return ResponseEntity.ok().body(null);
        }
    }
    

}
