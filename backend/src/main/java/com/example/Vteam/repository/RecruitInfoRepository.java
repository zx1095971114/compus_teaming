package com.example.Vteam.repository;

import com.example.Vteam.entity.History;
import com.example.Vteam.entity.RecruitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitInfoRepository extends JpaRepository<RecruitInfo,String> {
    @Query("select r from RecruitInfo r order by startTime desc")
    List<RecruitInfo> findAllOrderByStartTimeDesc();
}
