package com.example.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * MessageRepository interface
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUserName(String userName);
    List<Message> findAllByMsgContains(String msg);
    List<Message> findFirst1ByOrderByIdDesc();
    List<Message> findFirst5ByOrderByIdDesc();
}