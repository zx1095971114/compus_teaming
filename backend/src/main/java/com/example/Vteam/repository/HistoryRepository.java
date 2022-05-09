package com.example.Vteam.repository;

import com.example.Vteam.entity.History;
import com.example.Vteam.entity.VteamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History,String> {
    @Query("select h from History h where h.username=?1")
    List<History> findAllHistoryByUsername(String username);
}
