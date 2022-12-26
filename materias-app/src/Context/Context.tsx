
import {createContext} from 'react'
import { Curso } from '../model/Curso';
import { Grupo } from '../model/Grupo';
import { Materia } from '../model/Materia';

export interface tipos{
    route:string,
    setRoute:(c: string) => void,
    listaCursos:Curso[],
    setCurso:(c:Curso[]) => void,
    listaMaterias:Materia[] | [],
    setMaterias:(c:Materia[]) => void,
    listaGrupos: Grupo[] | [],
    setGrupos:(c:Grupo[]) => void,
}

let initialState:tipos = {
    route:"",
    setRoute:()=>{},
    listaCursos:[],
    setCurso:()=>{},
    listaMaterias:[],
    setMaterias:()=>{},
    listaGrupos:[],
    setGrupos:()=>{},
};




export const MateriasContext = createContext<typeof initialState>(initialState);
