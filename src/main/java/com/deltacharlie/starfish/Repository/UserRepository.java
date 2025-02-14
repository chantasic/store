package com.deltacharlie.starfish.Repository;

import com.deltacharlie.starfish.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    void deleteByName(String name);
}

