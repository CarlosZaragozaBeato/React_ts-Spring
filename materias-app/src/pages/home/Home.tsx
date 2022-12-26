import React,{useContext} from "react"; 
import { MateriasContext } from "../../Context/Context";
import routes from "../../utils/Routes";
import { CursosHome } from "./components/cursos/CursosHome";
import { GruposHome } from "./components/grupos/GruposHome";
import { MateriasHome } from "./components/materias/MateriasHome";
import './styles/Home.css'

function Home() {

    const valor = useContext(MateriasContext);
    valor.setRoute(routes.HOME)
        

    return (
            <main
            className="home_content">
                <h2 className="content_title">Home</h2>
                <div className="main_content">
                    <div className="first_part_content">
                        <CursosHome/>
                        <GruposHome />
                    </div>
                    <div>
                        <MateriasHome/>
                    </div>

                </div>

            </main>
    );   
  }
  
  export default Home;