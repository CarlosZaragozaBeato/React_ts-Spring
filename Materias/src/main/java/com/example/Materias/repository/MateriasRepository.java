package com.example.Materias.repository;

import com.example.Materias.model.Materias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriasRepository  extends JpaRepository<Materias, Integer> {
}
