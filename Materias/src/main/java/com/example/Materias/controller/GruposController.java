package com.example.Materias.controller;


import com.example.Materias.model.Grupos;
import com.example.Materias.repository.GruposRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GruposController {

private GruposRepository grpRepository;
private List<Grupos> listaGrupos;

public GruposController(GruposRepository grpRepository){
    this.grpRepository = grpRepository;
}
@GetMapping("/grupos")
public List<Grupos> findAll(){return listaGrupos =  grpRepository.findAll();}

//FindById
@GetMapping("/grupos/{codigoGrupo}")
public ResponseEntity<Grupos> findById(@PathVariable int codigoGrupo){
    Optional<Grupos> response = grpRepository.findById(codigoGrupo);
    return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
}
//FindByName
@GetMapping("/grupos/nombre/{nombreGrupo}")
public ResponseEntity<List<Grupos>> findByName(@PathVariable String nombreGrupo){

    List<Grupos> response = filtrarGrupos(nombreGrupo);
    if(response.size()>0){return ResponseEntity.ok(response);}
    return ResponseEntity.badRequest().build();
}
private List<Grupos> filtrarGrupos(String nombre){
    return listaGrupos.stream().filter((curso) -> {return curso.getNombre().contains(nombre);}).toList();
}

//Create
@PostMapping("/grupos/create")
public ResponseEntity create(@RequestBody Grupos grp){

    if (String.valueOf(grp.getClave()).isBlank()){return ResponseEntity.badRequest().build();}

    grpRepository.save(grp);
    return ResponseEntity.ok(grp);
}
//Update
@PutMapping("/grupos/update")
public ResponseEntity update(@RequestBody Grupos grp){

    Optional<Grupos> grupo= grpRepository.findById(grp.getClave());

    if (grupo.isEmpty()){return ResponseEntity.badRequest().build();}

    grpRepository.save(grp);
    return ResponseEntity.ok(grp);
}
//Delete
@DeleteMapping("/grupos/delete/{codigoGrupo}")
public ResponseEntity deleteById(@PathVariable int codigoGrupo){
    Optional<Grupos> mtr = grpRepository.findById(codigoGrupo);

    if (mtr.isEmpty()){return ResponseEntity.badRequest().build();}

    grpRepository.deleteById(codigoGrupo);
    return ResponseEntity.noContent().build();
}
@DeleteMapping("/grupos/deleteAll")
public ResponseEntity deleteAll(){

    List<Grupos> lista = grpRepository.findAll();

    if (lista.size() == 0){return ResponseEntity.badRequest().build();}

    grpRepository.deleteAll();
    return ResponseEntity.noContent().build();
}

}
