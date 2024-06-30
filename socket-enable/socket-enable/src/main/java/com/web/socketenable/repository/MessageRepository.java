package com.web.socketenable.repository;

import com.web.socketenable.entity.Message;
import com.web.socketenable.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndReceiverOrReceiverAndSenderOrderByTimestamp(
            User sender1, User receiver1, User sender2, User receiver2
    );
}
