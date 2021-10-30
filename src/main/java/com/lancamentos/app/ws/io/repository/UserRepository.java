package com.lancamentos.app.ws.io.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancamentos.app.ws.io.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserCodigo(String userId);
}
