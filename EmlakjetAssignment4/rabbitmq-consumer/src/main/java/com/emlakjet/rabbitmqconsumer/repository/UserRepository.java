package com.emlakjet.rabbitmqconsumer.repository;

import com.emlakjet.rabbitmqconsumer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}