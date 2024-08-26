package com.sutanu.spring_security_jwt_final.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sutanu.spring_security_jwt_final.entity.UserInfo;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer>{

	Optional<UserInfo> findByName(String username);
}
