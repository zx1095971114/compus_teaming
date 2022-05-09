package com.example.Vteam.repository;

import com.example.Vteam.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,String> {
}
