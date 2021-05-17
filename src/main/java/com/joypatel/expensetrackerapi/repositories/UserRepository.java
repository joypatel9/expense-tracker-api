package com.joypatel.expensetrackerapi.repositories;

import com.joypatel.expensetrackerapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    long countByEmail(String email);

    User findByEmail(String email);
}
