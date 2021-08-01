package com.example.ex4.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * UserRepository interface
 */
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUserName(String userName);
    User findByUserName(String userName);
}
