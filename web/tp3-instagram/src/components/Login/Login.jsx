import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import '../../styles/Login.css';
import logo from '../../images/fotoInstagram.png';
import logoig from '../../images/instagram-new-logo.png' ;
import axios from "axios";

const Login = () => {
    const history = useHistory();
    const [data, setData] = useState({
      email: "",
      password: "",
    });

    const handleInputChange = (event) => {
        setData({
          ...data,
          [event.target.name]: event.target.value,
        });
      };

    
    const handleSubmit = (event) => {
        event.preventDefault();
        axios.post("http://localhost:7000/login", data)
            .then((response) => {
                localStorage.setItem("token", response.headers.authorization);
                localStorage.setItem("userData", JSON.stringify(response.data));
                history.push("/home");
          })
          .catch((error) => console.log("Error: ", error));
      };



    return (
        <div className="container-fluid">

            <div className="row">
                <div id="columnaIzquierda" className="col-md-6">
                    <div id="fotoInstagram">
                        <div className="foto">
                        <img src={logo} className="celu"/>
                        </div> 
                    </div>
                </div>
                
                <div id="columnaDerecha" className="col-md-3 text-center">            
                    <img src={logoig} className="fontIg"/>
                        <form onSubmit={handleSubmit}>
                            <div id="login">
                                <div className="form-group" >
                                    <input className="form-control" type="text" name="email" value={data.email} onChange={handleInputChange} placeholder="Email" required />
                                </div>
                                <div className="form-group" >
                                    <input className="form-control" type="password" name="password" value={data.password} onChange={handleInputChange} placeholder="Password" required />
                                </div>
                                <div className="button">
                                    <button type="buttonLogin" class="btn btn-primary btn-lg btn-block">Iniciar sesion</button>
                                </div>
                            </div>
                        </form>
                            <div id="register">
                                <h6>¿ No tienes cuenta ?</h6>
                                <button type="buttonRegister" class="btn btn-link">Registrate</button>
                            </div> 
                           
                </div>
            </div>
        </div>
                            
    )
}

export default Login ;