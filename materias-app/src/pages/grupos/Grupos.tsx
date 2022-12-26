import React, { useContext, useState, useEffect,FormEvent } from "react";
import { MateriasContext } from "../../Context/Context";
import { Grupo } from "../../model/Grupo";
import routes from "../../utils/Routes";
import { GruposHomeItem } from "../home/components/grupos/GruposHome";
function Grupos() {
  const valor = useContext(MateriasContext);
  valor.setRoute(routes.GRUPOS);

    const [lista, setLista] = useState<Grupo[]>([]);
    const [listaFiltrada, setListaFiltrada] = useState<Grupo[]>([]);
    const [filter, setFilter] = useState<string>("");

    useEffect(() => {
        setLista(valor.listaGrupos);
    }, []);

    function filtrarGrupos() {
        const nueva = lista.filter((item) => {
        return item.clave.toString() == filter
        });
        setListaFiltrada(nueva)
    }

    const prevent = (e: FormEvent) => {
        e.preventDefault();
    };

    function handleChange(event: React.ChangeEvent<HTMLInputElement>) {
        setFilter(event.target.value)
    }


  return (
    <main>
      <h2 className="content_title">Grupos</h2>
      <div className="cursos_crud">
        <div className="page_cursos_container cursos_container">
          <div className="first_part">
            <h3 className="section_home_title">Grupos</h3>
            <form className="search_form" onSubmit={prevent}>
              <input
                type="text"
                className="search_input"
                value={filter}
                onChange={handleChange}
                placeholder="Filtrar Codigo"
              />

              <button className="search_button" onClick={filtrarGrupos}>
                Buscar
              </button>
            </form>
          </div>

          {listaFiltrada.length == 0
            ? lista.map((grupo) => {
                return GruposHomeItem(grupo);
              })
            : listaFiltrada.map((grupo) => {
                return GruposHomeItem(grupo);
              })}
        </div>
        <div className="form_cursos">
          <h2>CRUD GRUPOS</h2>
          <form className="form_crs">
            <div className="forms_inputs">
              <input type="text" placeholder="Clave..." />
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
  );
}

export default Grupos;
