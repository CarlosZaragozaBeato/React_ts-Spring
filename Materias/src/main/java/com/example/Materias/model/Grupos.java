package com.example.Materias.model;


import jakarta.persistence.*;

@Entity
@Table(name= "Grupos")
public class Grupos {

    @Id
    private int clave;
    @Column
    private String nombre;

    @ManyToOne(targetEntity = Curso.class)
    private Curso codigoCurso;


    public Grupos(int clave, String nombre, Curso codigoCurso){
        this.clave = clave;
        this.nombre = nombre;
        this.codigoCurso = codigoCurso;
    }

    public Grupos(int clave, String nombre){
        this.clave = clave;
        this.nombre = nombre;

    }
    public Grupos(){}


    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Curso getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(Curso codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    @Override
    public String toString() {
        return "Grupos{" +
                ", clave=" + clave +
                ", nombre='" + nombre + '\'' +
                ", codigoCurso=" + codigoCurso +
                '}';
    }
}
