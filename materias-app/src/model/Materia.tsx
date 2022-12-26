import { Curso } from "./Curso";

    export interface Materia {
        clave:number,
        iso:string,
        departamento:string,
        nombre:string,
        curso:Curso
      }