package com.example.Vteam.repository;

import com.example.Vteam.entity.VteamUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<VteamUser,String> {

}
