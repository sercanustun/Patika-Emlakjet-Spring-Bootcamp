package com.emlakjet.rabbitmqproducer.repository;

import com.emlakjet.rabbitmqproducer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
