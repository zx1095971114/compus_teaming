package com.example.Vteam.repository;

import com.example.Vteam.entity.History;
import com.example.Vteam.entity.VteamInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VteamInfoRepository extends JpaRepository<VteamInfo,String> {
}
