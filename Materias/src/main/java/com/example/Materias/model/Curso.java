package com.example.Materias.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Curso", schema = "public")
public class Curso {

    @Id
    private int codigoCurso;
    @Column
    private String abreviaturaCurso;
    @Column
    private String descripcionCurso;


    public Curso(int codigoCurso, String abreviaturaCurso, String descripcionCurso) {
        this.codigoCurso = codigoCurso;
        this.abreviaturaCurso = abreviaturaCurso;
        this.descripcionCurso = descripcionCurso;
    }

    public Curso() {}

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getAbreviaturaCurso() {
        return abreviaturaCurso;
    }

    public void setAbreviaturaCurso(String abreviaturaCurso) {
        this.abreviaturaCurso = abreviaturaCurso;
    }

    public String getDescripcionCurso() {
        return descripcionCurso;
    }

    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigoCurso=" + codigoCurso +
                ", abreviaturaCurso='" + abreviaturaCurso + '\'' +
                ", descripcionCurso='" + descripcionCurso + '\'' +
                '}';
    }
}
