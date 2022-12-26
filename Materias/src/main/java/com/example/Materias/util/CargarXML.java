package com.example.Materias.util;

import com.example.Materias.model.Curso;
import com.example.Materias.model.Grupos;
import com.example.Materias.model.Materias;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class  CargarXML {
    //*********************Materias*****************************//
    public static void recuperarMaterias(String ruta, ArrayList<Curso> listaCursos, ArrayList<Materias> listaMaterias) {

        Element raiz = null;

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(ruta);

            raiz = doc.getDocumentElement();

            NodeList nlGrupo = doc.getElementsByTagName("listasal");

            Element elemento;

            String[] trozos = null;
            String atributo = null;


            for (int i = 0; i < nlGrupo.getLength(); i++) {
                atributo = nlGrupo.item(i).getAttributes().item(0).getTextContent();
                if (atributo.contains("MATERIAS_")) {

                    trozos = atributo.split("MATERIAS_");

                    elemento = (Element) nlGrupo.item(i);
                    Curso crs =  ObtenerCurso(elemento);
                    if (comprobarCurso(listaCursos, crs)){
                        listaCursos.add(crs);
                    }
                    listaMaterias.add(obtenerMaterias(elemento,listaCursos));

                }
            }
        } catch (Exception e) {System.out.println(e);}

    }
    public static Materias obtenerMaterias(Element l,ArrayList<Curso> listaCursos) {

        String nombre, Iso, departamento;
        int codigoCurso, clave;

        Element el = null;
        NodeList lista = null;

        Materias mtr = null;

        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(0);
        nombre = el.getTextContent();

        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(1);
        clave = Integer.parseInt(el.getTextContent());

        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(2);
        Iso = el.getTextContent();

        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(3);

        if(el.getTextContent().contains("<")){codigoCurso = Integer.parseInt(el.getTextContent().replace("<", ""));}
        else{codigoCurso = Integer.parseInt(el.getTextContent());}

        Curso crs =  ObtenerCursoMaterias(codigoCurso,listaCursos);


        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(6);
        departamento = el.getTextContent();

        mtr = new Materias(nombre, clave, Iso, crs, departamento);
        return mtr;
    }
    public static Curso ObtenerCursoMaterias(int codigoCurso, ArrayList<Curso> listaCursos){
            List<Curso> crs = listaCursos.stream().filter((curso) -> {
                return curso.getCodigoCurso() == codigoCurso;
            }).toList();
        return crs.get(0);
    }
    //*********************Grupos*****************************//
    public static ArrayList<Grupos> recuperarGrupos(String ruta, ArrayList<Curso> listaCursos){
        ArrayList<Grupos> almacenGrupos = new ArrayList<Grupos>();

        Element raiz = null;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(ruta);

            raiz = doc.getDocumentElement();

            NodeList nlGrupo = doc.getElementsByTagName("listasal");

            Element elementoGrupo;

            String[] trozos = null;
            String atributo = null;

            for (int i = 0; i < nlGrupo.getLength(); i++) {
                atributo = nlGrupo.item(i).getAttributes().item(0).getTextContent();
                if (atributo.contains("GRUPOS_")) {

                    trozos = atributo.split("GRUPOS_");

                    elementoGrupo = (Element) nlGrupo.item(i);

                    almacenGrupos.add(obtenerGrupos(elementoGrupo, listaCursos));
                }
            }
        } catch (Exception e) {System.out.println(e);}

        return almacenGrupos;
    }
    public static Grupos obtenerGrupos(Element l , ArrayList<Curso> listaCursos){

        String nombre;
        int clave, curso;

        Element el = null;
        NodeList lista = null;
        Grupos grp = null;


        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(0);
        clave = Integer.parseInt(el.getTextContent());

        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(1);
        nombre = el.getTextContent();


        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(2);
        curso = Integer.parseInt(el.getTextContent());

        Curso crs = ObtenerCursoGrupos(curso, listaCursos);

        grp = new Grupos(clave, nombre, crs);

        return grp;
    }
    public static Curso ObtenerCursoGrupos(int codigoCurso, ArrayList<Curso> listaCursos){
        List<Curso> crs = listaCursos.stream().filter((curso) -> {
            return curso.getCodigoCurso() == codigoCurso;
        }).toList();
        return crs.get(0);
    }
    //*********************Cursos*****************************//
    public static boolean comprobarCurso(ArrayList<Curso> lista_curso, Curso crs){
        boolean condition = true;

        for(Curso curso: lista_curso){
            if(curso.getCodigoCurso() == crs.getCodigoCurso()){
                condition = false;
                break;
            }
        }
        return condition;
    }
    public static Curso ObtenerCurso(Element l){

        String descripcion, abreviatura;
        int codigoCurso;

        Element el = null;
        NodeList lista = null;

        Curso crs = null;


        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(3);
        if(el.getTextContent().contains("<")){codigoCurso = Integer.parseInt(el.getTextContent().replace("<", ""));}
        else{codigoCurso = Integer.parseInt(el.getTextContent());}


        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(4);
        abreviatura = el.getTextContent();

        lista = l.getElementsByTagName("salida");
        el = (Element) lista.item(5);
        descripcion = el.getTextContent();

        crs = new Curso(codigoCurso, abreviatura, descripcion);
        return crs;
    }



}
