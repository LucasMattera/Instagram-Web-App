import React, { useState } from "react";
import '../../styles/Login.css';
import logo from '../../images/fotoInstagram.png';
import logoig from '../../images/instagram-new-logo.png' ;

const Login = () => {
    const [email,setEmail] = useState("") ;
    const [password,setPassword] = useState("") ;


    return (
        <div className="container-fluid">

            <div className="row">
                <div id="columnaIzquierda" className="col-md-3">
                    <div id="fotoInstagram">
                        <div className="foto">
                        <img src={logo} className="celu"/>
                        </div> 
                    </div>
                </div>
                
                <div id="columnaDerecha" className="col-md-3 text-center">            
                    <img src={logoig} className="fontIg"/>
                        <form>
                            <div id="login">
                                <div className="form-group" >
                                    <input class="form-control" type="text" name="email" placeholder="Email" required />
                                </div>
                                <div className="form-group" >
                                    <input class="form-control" type="password" name="password" placeholder="Password" required />
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