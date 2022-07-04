package com.ecomm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.entities.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	Optional<UserAccount> findByUserId(Long userId);
}
