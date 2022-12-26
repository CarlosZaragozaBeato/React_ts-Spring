package com.example.Materias.controller;


import com.example.Materias.model.Curso;
import com.example.Materias.repository.CursosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController {

private CursosRepository crsRepository;
private List<Curso> listaCursos;

public CursoController(CursosRepository crsRepository){
    this.crsRepository = crsRepository;
}
@GetMapping("/cursos")
public List<Curso> findAll(){
    listaCursos =  crsRepository.findAll();
    return listaCursos;
}

//FindById
@GetMapping("/cursos/{codigoCurso}")
public ResponseEntity<Curso> findById(@PathVariable int codigoCurso){

    Optional<Curso> response = crsRepository.findById(codigoCurso);
    return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
}
//FindByName
@GetMapping("/cursos/nombre/{nombreCurso}")
public ResponseEntity<List<Curso>> findByName(@PathVariable String nombreCurso){

    List<Curso> response = filtrarCurso(nombreCurso);
    if(response.size()>0){return ResponseEntity.ok(response);}
    return ResponseEntity.badRequest().build();
}
private List<Curso> filtrarCurso(String nombre){
    return listaCursos.stream().filter((curso) -> {
                return curso.getDescripcionCurso().contains(nombre);
            }).toList();
}

//Create
@PostMapping("/cursos/create")
public ResponseEntity create(@RequestBody Curso crs){

    if (String.valueOf(crs.getCodigoCurso()).isBlank()){
        return ResponseEntity.badRequest().build();
    }
    crsRepository.save(crs);
    return ResponseEntity.ok(crs);
}
//Update
@PutMapping("/cursos/update")
public ResponseEntity update(@RequestBody Curso crs){

    Optional<Curso> curso= crsRepository.findById(crs.getCodigoCurso());

    if (curso.isEmpty()){
        return ResponseEntity.badRequest().build();
    }
    crsRepository.save(crs);
    return ResponseEntity.ok(crs);
}
//Delete
@DeleteMapping("/cursos/delete/{codigoCurso}")
public ResponseEntity deleteById(@PathVariable int codigoCurso){
    Optional<Curso> crs = crsRepository.findById(codigoCurso);

    if (crs.isEmpty()){
        return ResponseEntity.badRequest().build();
    }
    crsRepository.deleteById(codigoCurso);
    return ResponseEntity.noContent().build();
}
@DeleteMapping("/cursos/deleteAll")
public ResponseEntity deleteAll(){

    List<Curso> lista = crsRepository.findAll();

    if (lista.size() == 0){
        return ResponseEntity.badRequest().build();
    }
    crsRepository.deleteAll();
    return ResponseEntity.noContent().build();
}















}
