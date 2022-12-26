package com.example.Materias.repository;

import com.example.Materias.model.Grupos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GruposRepository extends JpaRepository<Grupos,Integer> {}
