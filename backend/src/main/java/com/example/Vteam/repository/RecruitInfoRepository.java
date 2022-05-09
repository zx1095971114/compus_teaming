package com.example.Vteam.repository;

import com.example.Vteam.entity.History;
import com.example.Vteam.entity.RecruitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitInfoRepository extends JpaRepository<RecruitInfo, String> {

    @Query("select r from RecruitInfo  r where r.creator=?1 and r.isDestroy=0")
    List<RecruitInfo> findRecruitInfoByUsernameAndIsDestroy(String username);

    @Query("select r from RecruitInfo  r where r.tid=?1")
    List<RecruitInfo> findRecruitInfoByTid(String tid);

    @Query("select r from RecruitInfo r order by r.startTime desc")
    List<RecruitInfo> findAllOrderByStartTimeDesc();
}
