package com.example.Materias.controller;

import com.example.Materias.MateriasApplication;
import com.example.Materias.model.Curso;
import com.example.Materias.model.Grupos;
import com.example.Materias.model.Materias;
import com.example.Materias.repository.CursosRepository;
import com.example.Materias.repository.GruposRepository;
import com.example.Materias.repository.MateriasRepository;
import com.example.Materias.util.CargarXML;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    MateriasRepository mtrRepository;
    GruposRepository grpRepository;
    CursosRepository crsRepository;


    public HomeController(MateriasRepository mtrRepository, GruposRepository grpRepository, CursosRepository crsRepository) {
        this.mtrRepository = mtrRepository;
        this.grpRepository = grpRepository;
        this.crsRepository = crsRepository;
    }


    /**
     * Carga Inicial de datos
     */
    @GetMapping("/")
    public List<Object> home(){
        ArrayList<Materias> listaMaterias = new ArrayList();
        ArrayList<Curso> listaCursos = new ArrayList<>();
        ArrayList<Grupos> listaGrupos = new ArrayList<>();
        List<Object> listaObjectos = new ArrayList<>();

        if(ComprobarBD()) {
            CargarXML.recuperarMaterias(MateriasApplication.ruta, listaCursos, listaMaterias);
            listaGrupos = CargarXML.recuperarGrupos(MateriasApplication.ruta,listaCursos);

            listaCursos.stream().forEach((crs) -> {crsRepository.save(crs);});
            listaGrupos.stream().forEach((grp) -> {grpRepository.save(grp);});
            listaMaterias.stream().forEach((mtr) -> {mtrRepository.save(mtr);});
        }else{
            listaCursos = (ArrayList<Curso>) crsRepository.findAll();
            listaGrupos = (ArrayList<Grupos>) grpRepository.findAll();
            listaMaterias = (ArrayList<Materias>) mtrRepository.findAll();
        }
        listaObjectos.add(listaCursos);
        listaObjectos.add(listaGrupos);
        listaObjectos.add(listaMaterias);

        System.out.println("asd");
        return listaObjectos;
    }

    /**
     * Comprobacion de las bd
     * @return
     */
    private boolean ComprobarBD(){return crsRepository.findAll().size() == 0;}
}
