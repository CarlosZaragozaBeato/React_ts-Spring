
import React,{ useContext, useState, useEffect, FormEvent } from "react";
import uuid from "react-uuid";
import { MateriasContext } from "../../../../Context/Context";
import { Grupo } from "../../../../model/Grupo";
import Group from '../../../../static/images/group.png';
import './GruposHome.css'

export const GruposHome = () => {
  const valor = useContext(MateriasContext);
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
      <>
        {lista.length!=0?(<div className="grupos_container">
        <div className="first_part"> 
               <h3 className="section_home_title">Grupos</h3>
               <form className="search_form" onSubmit={prevent}>
                  <input type="text" 
                  className="search_input" 
                  onChange={handleChange}
                  placeholder="Filtrar Clave"/>
                  <button className="search_button" onClick={filtrarGrupos}>Buscar</button>
               </form>
        </div>
      {listaFiltrada.length == 0 ?lista.map((grupo) => {return GruposHomeItem(grupo);}):
      listaFiltrada.map((grupo) => {return GruposHomeItem(grupo);})}
    </div>):("")}
      </>
  );
};

export const GruposHomeItem = (grp: Grupo) => {
  return (
    <div key={uuid()} className="card_content">
        <div className="card_content_header">
            <img src={Group} className="card_image"/>
            <p className='card_title'>{grp.nombre}</p>
        </div>
        <p>Clave: {grp.clave}</p>
        <p>Codigo Curso: {grp.codigoCurso.codigoCurso}</p>
    </div>
  );
};
