package com.example.Vteam.repository;

import com.example.Vteam.entity.History;
import com.example.Vteam.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,String> {
    @Query("select m from Message m where m.username=?1")
    List<Message> findAllMessageByUsername(String username);


}
