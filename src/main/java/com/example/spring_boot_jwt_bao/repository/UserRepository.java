package com.example.spring_boot_jwt_bao.repository;

import com.spring_boot_jwt_bao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Bao> {
    Optional<User> findUserByUsername(String username);

    Boolean existsByUsername(String username);
}
