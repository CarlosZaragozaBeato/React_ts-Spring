import { Materia } from "../../../../model/Materia";
import React, { useContext, useState, useEffect, FormEvent } from "react";
import { MateriasContext } from "../../../../Context/Context";
import uuid from "react-uuid";
import Book from "../../../../static/images/subject.png";
import "./MateriasHome.css";

export const MateriasHome = () => {
  const valor = useContext(MateriasContext);
  const [lista, setLista] = useState<Materia[]>([]);
  const [listaFiltrada, setListaFiltrada] = useState<Materia[]>([]);
  const [filter, setFilter] = useState<string>("");

  useEffect(() => {
    setLista(valor.listaMaterias);
  }, []);

  function filtrarMaterias() {
    const nueva = lista.filter((item) => {
      return item.clave.toString() == filter;
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
        <div className="materias_center">
          <div className="materias_container">
            <div className="first_part">
              <h3 className="section_home_title">Materias</h3>
              <form className="search_form" onSubmit={prevent}>
                <input
                  type="text"
                  className="search_input"
                  value={filter}
                  onChange={handleChange}
                  placeholder="Filtrar Clave"
                />

                <button className="search_button" onClick={filtrarMaterias}>
                  Buscar
                </button>
              </form>
            </div>
            {listaFiltrada.length == 0
              ? lista.map((materia) => {
                  return MateriasHomeItem(materia);
                })
              : listaFiltrada.map((materia) => {
                  return MateriasHomeItem(materia);
                })}
          </div>
        </div>
      ) : (
        ""
      )}
    </>
  );
};

export const MateriasHomeItem = (mtr: Materia) => {
  return (
    <div key={uuid()} className="card_content">
      <div className="card_content_header">
        <img src={Book} className="card_image" />
        <div className="content_header">
          <p className="card_title">{mtr.nombre}</p>
          <p className="card_title">{mtr.iso}</p>
        </div>
      </div>
      <p className="depar">Departamento: {mtr.departamento}</p>
      <p>Clave: {mtr.clave}</p>
      <p>Codigo Curso: {mtr.curso.codigoCurso}</p>
    </div>
  );
};
