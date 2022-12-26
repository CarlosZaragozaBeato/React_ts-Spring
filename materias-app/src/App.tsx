
import axios from "axios";
import React, {useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Routes, UNSAFE_enhanceManualRouteObjects } from "react-router-dom";
import NavBar from "./Components/NavBar/NavBar";
import { MateriasContext, tipos } from "./Context/Context";
import { Curso } from "./model/Curso";
import { Grupo } from "./model/Grupo";
import { Materia } from "./model/Materia";


import Cursos from "./pages/cursos/Cursos";
import Grupos from "./pages/grupos/Grupos";
import Home from "./pages/home/Home";
import Materias from "./pages/materias/Materias";



import routes from "./utils/Routes";

function App() {

const [route, setRoute] = useState<string>(routes.HOME)

  const [cursos, setCursos] =useState<Curso[]>([])
  const [grupos, setGrupos] =useState<Grupo[]>([])
  const [materias, setMaterias] =useState<Materia[]>([])
  let [condition, setCondition] = useState<boolean>(false)


  async function  getData () {
    await axios.get("http://localhost:8085", {
        withCredentials: false,
      })
      .then((response) => {
        setCursos(response.data[0])
        setGrupos(response.data[1])
        setMaterias(response.data[2])
        setCondition(true)
      })
      .catch((e) => {
        setCondition(false)
      })

    };

useEffect(()=>{
  getData()
}, [])


  let valor:tipos = {
    route: route,
    setRoute:setRoute,
    listaCursos: cursos,
    setCurso:setCursos,
    listaGrupos: grupos,
    setGrupos:setGrupos,
    listaMaterias: materias,
    setMaterias:setMaterias
  }



  return (
    <div className="App">
      <Router>
        <MateriasContext.Provider value={valor}>
          <NavBar />
          <Routes>
            <Route path={routes.HOME} element={condition?<Home />:""} />
            <Route path={routes.CURSOS} element={<Cursos />} />
            <Route path={routes.GRUPOS} element={<Grupos />} />
            <Route path={routes.MATERIAS} element={<Materias />} />
            <Route path="*" element={<h1>ASD</h1>} />
          </Routes>
        </MateriasContext.Provider>
      </Router>
    </div>
  );
}
export default App;




