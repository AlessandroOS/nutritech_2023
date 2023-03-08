package com.alessandro.nutritech2023.repository;

import com.alessandro.nutritech2023.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
