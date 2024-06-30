package com.web.socketenable.service;

import com.web.socketenable.entity.Message;
import com.web.socketenable.entity.User;
import com.web.socketenable.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public List<Message> findMessagesByUsers(User user1, User user2) {
        return messageRepository.findBySenderAndReceiverOrReceiverAndSenderOrderByTimestamp(user1, user2, user2, user1);
    }

    public Message saveMessage(Message message) {
        Message savedMessage = messageRepository.save(message);
        messagingTemplate.convertAndSend("/topic/chat/" + message.getReceiver().getUsername(), savedMessage);
        return savedMessage;
    }
}
