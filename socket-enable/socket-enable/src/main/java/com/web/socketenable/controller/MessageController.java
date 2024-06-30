package com.web.socketenable.controller;

import com.web.socketenable.entity.Message;
import com.web.socketenable.entity.User;
import com.web.socketenable.service.MessageService;
import com.web.socketenable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @GetMapping("/conversation")
    public List<Message> getConversation(@RequestParam String user1, @RequestParam String user2) {
        User sender = userService.findByUsername(user1);
        User receiver = userService.findByUsername(user2);
        return messageService.findMessagesByUsers(sender, receiver);
    }
    @PostMapping("/send")
    public void sendMessage(@Payload Message message) {
        messageService.saveMessage(message);
    }
}
