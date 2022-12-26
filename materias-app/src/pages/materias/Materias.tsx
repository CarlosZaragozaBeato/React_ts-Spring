import React, { useContext, useState, useEffect, FormEvent } from "react";
import { MateriasContext } from "../../Context/Context";
import { Materia } from "../../model/Materia";
import routes from "../../utils/Routes";
import { MateriasHomeItem } from "../home/components/materias/MateriasHome";
function Materias() {
  const valor = useContext(MateriasContext);
  valor.setRoute(routes.MATERIAS);

  const [lista, setLista] = useState<Materia[]>([]);
  const [listaFiltrada, setListaFiltrada] = useState<Materia[]>([]);
  const [filter, setFilter] = useState<string>("");

  function filtrarCursos() {
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

  useEffect(() => {
    setLista(valor.listaMaterias);
  }, []);

  return (
    <>
      <main>
        <h2 className="content_title">Materias</h2>
        <div className="cursos_crud">
          <div className="page_cursos_container cursos_container">
            <div className="first_part">
              <h3 className="section_home_title">Materias</h3>
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
              ? lista.map((materia) => {
                  return MateriasHomeItem(materia);
                })
              : listaFiltrada.map((materia) => {
                  return MateriasHomeItem(materia);
                })}
          </div>
          <div className="form_cursos">
            <h2>CRUD MATERIAS</h2>
            <form className="form_crs">
              <div className="forms_inputs">
                <input type="text" placeholder="Clave..." />
                <input type="text" placeholder="Iso..." />
                <input type="text" placeholder="Departamento..." />
                <input type="text" placeholder="Nombre..." />
                <input type="text" placeholder="Codigo Curso..." />
              </div>
              <div className="forms_buttons">
                <button>CREAR</button>
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

export default Materias;
