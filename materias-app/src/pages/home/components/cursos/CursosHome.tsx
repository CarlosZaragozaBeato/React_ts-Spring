import React, { useContext, useState, useEffect, FormEvent } from "react";
import Course from "../../../../static/images/course.png";
import "./CursosHome.css";
import { Curso } from "../../../../model/Curso";
import { MateriasContext } from "../../../../Context/Context";
export function CursosHome() {
  const valor = useContext(MateriasContext);
  const [lista, setLista] = useState<Curso[]>([]);
  const [listaFiltrada, setListaFiltrada] = useState<Curso[]>([]);
  const [filter, setFilter] = useState<string>("");

  useEffect(() => {
    setLista(valor.listaCursos);
  }, []);

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

  return (
    <>
      {lista.length != 0 ? (
        <div className="cursos_container">
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
      ) : (
        ""
      )}
    </>
  );
}

export const CursosHomeItem = (crs: Curso) => {
  return (
    <div className="card_content">
      <div className="card_content_header">
        <img src={Course} className="card_image" />
        <p className="card_title">{crs.descripcionCurso}</p>
      </div>
      <p>Codigo Curso: {crs.codigoCurso}</p>
      <p>Abreviatura: {crs.abreviaturaCurso}</p>
    </div>
  );
};
