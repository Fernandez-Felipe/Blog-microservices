package com.felipe.blog.auth_service.Repository;

import com.felipe.blog.auth_service.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUserName(String username);

    Optional<User> findUserByEmail(String email);

}
