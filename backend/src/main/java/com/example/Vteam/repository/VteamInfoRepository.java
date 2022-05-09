package com.example.Vteam.repository;


import com.example.Vteam.entity.VteamInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VteamInfoRepository extends JpaRepository<VteamInfo, String> {
    @Query("select t from VteamInfo  t where t.teamMates like %?1% and t.isSuccess=0")
    List<VteamInfo> findVTeamInfoByUsernameAndIsSuccess(String username);
}
