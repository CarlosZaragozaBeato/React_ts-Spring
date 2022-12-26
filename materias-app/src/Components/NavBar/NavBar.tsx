import { Link } from "react-router-dom";
import React, {useContext} from "react";
import BookImage from "../../static/images/libro.png";

import routes from "../../utils/Routes";

import "./NavBar.css";
import { MateriasContext } from "../../Context/Context";

const NavBar = () => {

  const { route } = useContext(MateriasContext);
  return (
    <header className="header">
      <Link to={routes.HOME} className={`link_container`}>
        <div className="title_header">
          <img src={BookImage} alt="Book icon" />
          <h1 className={`${route == routes.HOME?"current":""}`}>Materias App</h1>
        </div>
      </Link>

      <nav className={`menu_bar`}>
        <Link to={routes.CURSOS} className={`${route == routes.CURSOS?"current":""} link`} >Cursos</Link>
        <Link to={routes.GRUPOS} className={`${route == routes.GRUPOS?"current":""} link`}>Grupos</Link>
        <Link to={routes.MATERIAS} className={`${route == routes.MATERIAS?"current":""} link`}>Materias</Link>
      </nav>
    </header>
  );
};
export default NavBar;
