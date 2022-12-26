package com.example.Materias.model;

import jakarta.persistence.*;


@Entity
@Table(name= "Materias")
public class Materias {
    @Id
    private int clave;
    @Column
    private String nombre;
    @Column
    private String ISO;
    @ManyToOne(targetEntity = Curso.class)
    private Curso curso;
    @Column
    private String departamento;

    public Materias(){}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getISO() {
        return ISO;
    }

    public void setISO(String ISO) {
        this.ISO = ISO;
    }

    public Materias(String nombre, int clave, String ISO, Curso curso, String departamento) {
        this.nombre = nombre;
        this.clave = clave;
        this.ISO = ISO;
        this.curso = curso;
        this.departamento = departamento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Materias{" +
                "clave=" + clave +
                ", nombre='" + nombre + '\'' +
                ", ISO='" + ISO + '\'' +
                ", curso=" + curso +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
