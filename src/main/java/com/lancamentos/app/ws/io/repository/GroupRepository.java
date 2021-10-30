package com.lancamentos.app.ws.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancamentos.app.ws.io.model.Grupo;

@Repository
public interface GroupRepository extends JpaRepository<Grupo, Long>{

}
