package com.example.Vteam.repository;

import com.example.Vteam.entity.History;
import com.example.Vteam.entity.VteamUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,String> {
}
