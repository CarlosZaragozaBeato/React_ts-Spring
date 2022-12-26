import axios from "axios";
import React, { useContext, useState, useEffect, FormEvent } from "react";
import { MateriasContext } from "../../Context/Context";
import { Curso } from "../../model/Curso";
import routes from "../../utils/Routes";
import { CursosHomeItem } from "../home/components/cursos/CursosHome";
import "./Cursos.css";
function Cursos() {
  const valor = useContext(MateriasContext);
  valor.setRoute(routes.CURSOS);
  const [lista, setLista] = useState<Curso[]>([]);
  const [listaFiltrada, setListaFiltrada] = useState<Curso[]>([]);
  const [filter, setFilter] = useState<string>("");

  const [codigoCurso,setCodigoCurso] = useState<string>("")
  const [abreviaturaCurso,setAbreviaturaCurso] = useState<string>("")
  const [descripcionCurso,setDescripcionCurso] = useState<string>("")
  const [error,setError] = useState<boolean>(false)
  function filtrarCursos() {
    const nueva = lista.filter((item) => {
      return item.codigoCurso.toString() == filter;
    });
    setListaFiltrada(nueva);
  }

  const prevent = (e: FormEvent) => {
    e.preventDefault();
  };

  function handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    setFilter(event.target.value);
  }

  function handleCodigo(event: React.ChangeEvent<HTMLInputElement>) {
    setCodigoCurso(event.target.value);
  }

  function handleAbreviatura(event: React.ChangeEvent<HTMLInputElement>) {
    setAbreviaturaCurso(event.target.value);
  }

  function handleDescripcion(event: React.ChangeEvent<HTMLInputElement>) {
    setDescripcionCurso(event.target.value);
  }

  useEffect(() => {
    setLista(valor.listaCursos);
  }, []);



  function crearCurso(){
    if(codigoCurso.length!=0&&
      abreviaturaCurso.length!=0&&
      descripcionCurso.length!=0){
        /*
      axios.post('http://localhost:8085/cursos/create', {
        codigoCurso: parseInt(codigoCurso),
        abreviaturaCurso: abreviaturaCurso,
        descripcionCurso:descripcionCurso
      },)
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });*/

      const data:object = {
        "abreviaturaCurso": abreviaturaCurso,
        "codigoCurso": parseInt(codigoCurso),
        "descripcionCurso":descripcionCurso
      }


    fetch("http://localhost:8085/cursos/create", {
      method: 'POST', // or 'PUT'
      body: JSON.stringify(data), // data can be `string` or {object}!
      mode: 'no-cors', 
      headers:{
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(res => {res.json()})
    .catch(error => console.error('Error:', error))
    .then(response => console.log('Success:', response));

    }else{

    }
  }


  return (
    <>
      <main>
        <h2 className="content_title">Cursos</h2>

        <div className="cursos_crud">
          <div className="page_cursos_container cursos_container">
            <div className="first_part">
              <h3 className="section_home_title">Cursos</h3>
              <form className="search_form" onSubmit={prevent}>
                <input
                  type="text"
                  className="search_input"
                  value={filter}
                  onChange={handleChange}
                  placeholder="Filtrar Codigo"
                />

                <button className="search_button" onClick={filtrarCursos}>
                  Buscar
                </button>
              </form>
            </div>

            {listaFiltrada.length == 0
              ? lista.map((curso) => {
                  return CursosHomeItem(curso);
                })
              : listaFiltrada.map((curso) => {
                  return CursosHomeItem(curso);
                })}
          </div>
          <div className="form_cursos">
            <h2>CRUD CURSOS</h2>
            <form className="form_crs" onSubmit={prevent}>
                <div className="forms_inputs">
                  <input type="text" 
                  placeholder="Codigo Curso..."
                  onChange={handleCodigo} />
                  <input type="text" 
                  placeholder="Descripcion..."
                  onChange={handleDescripcion} />
                  <input type="text" 
                  placeholder="Abreviatura Curso..." 
                  onChange={handleAbreviatura}/>
                </div>
                {error?(<p className="error">ERROR</p>):""}
                <div className="forms_buttons">
                  <button onClick={crearCurso}>CREAR</button>
                  <button>EDITAR</button>
                  <button>BORRAR</button>
              </div>
            </form>
          </div>
        </div>
      </main>
    </>
  );
}

export default Cursos;
