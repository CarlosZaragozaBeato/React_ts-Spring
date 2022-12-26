package com.example.Materias.controller;


import com.example.Materias.model.Materias;
import com.example.Materias.repository.MateriasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class MateriasController {

private MateriasRepository mtrRepository;
private List<Materias> listaMaterias;

public MateriasController(MateriasRepository mtrRepository){
    this.mtrRepository = mtrRepository;
}
@GetMapping("/materias")
public List<Materias> findAll(){return listaMaterias =  mtrRepository.findAll();}

//FindById
@GetMapping("/materias/{codigoMateria}")
public ResponseEntity<Materias> findById(@PathVariable int codigoMateria){

    Optional<Materias> response = mtrRepository.findById(codigoMateria);
    return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
}
//FindByName
@GetMapping("/materias/nombre/{nombreMateria}")
public ResponseEntity<List<Materias>> findByName(@PathVariable String nombreMateria){

    List<Materias> response = filtrarMaterias(nombreMateria);
    if(response.size()>0){return ResponseEntity.ok(response);}
    return ResponseEntity.badRequest().build();
}
private List<Materias> filtrarMaterias(String nombre){
    List<Materias> listaMtr =  listaMaterias.stream().filter((curso) -> {
                return curso.getNombre().contains(nombre);
            }).toList();
    return listaMtr;
}

//Create
@PostMapping("/materias/create")
public ResponseEntity create(@RequestBody Materias mtr){

    if (String.valueOf(mtr.getClave()).isBlank()){
        return ResponseEntity.badRequest().build();
    }
    mtrRepository.save(mtr);
    return ResponseEntity.ok(mtr);
}
//Update
@PutMapping("/materias/update")
public ResponseEntity update(@RequestBody Materias mtr){

    Optional<Materias> materia= mtrRepository.findById(mtr.getClave());

    if (materia.isEmpty()){
        return ResponseEntity.badRequest().build();
    }
    mtrRepository.save(mtr);
    return ResponseEntity.ok(mtr);
}
//Delete
@DeleteMapping("/materias/delete/{codigoMateria}")
public ResponseEntity deleteById(@PathVariable int codigoMateria){
    Optional<Materias> mtr = mtrRepository.findById(codigoMateria);

    if (mtr.isEmpty()){
        return ResponseEntity.badRequest().build();
    }
    mtrRepository.deleteById(codigoMateria);
    return ResponseEntity.noContent().build();
}
@DeleteMapping("/materias/deleteAll")
public ResponseEntity deleteAll(){
    List<Materias> lista = mtrRepository.findAll();

    if (lista.size() == 0){
        return ResponseEntity.badRequest().build();
    }
    mtrRepository.deleteAll();
    return ResponseEntity.noContent().build();
}
}
