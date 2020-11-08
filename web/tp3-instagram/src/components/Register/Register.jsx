import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import Notifications,{notify} from 'react-notify-toast';
import axios from "axios";

const Register = () => {
    const history = useHistory();
    const [data, setData] = useState({
        name: "",
        email: "",
        password: "",
        image: ""
    });

    let myColor = { background: '#0E1717', text: "#FFFFFF" };

    const handleInputChange = (event) => {
        setData({
            ...data,
            [event.target.name]: event.target.value,
        });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        axios.post("http://localhost:7000/register", data)
            .then((response) => {    
                localStorage.setItem("token", response.headers.authorization);
                axios.defaults.headers['authorization'] = localStorage.getItem('token')
                history.push("/");       
            })
            .catch(error => {
                console.log("error : ", error.response.data.message);
                const errorUser = error.response.data.message ;
                notify.show(errorUser,"error",5000,myColor);          
            });
        
    };
 
    return (
        <div className="container-fluid">
            <Notifications />
            <div className="row">
                
                <div id="columnaMedio" className="text-center">            
                    <img className="fontIg"/>
                        <form onSubmit={handleSubmit}>
                            <div id="register">
                                <div className="form-group" >
                                    <input className="form-control" type="text" name="name" value={data.name} onChange={handleInputChange} placeholder="Name" required />
                                </div>
                                <div className="form-group" >
                                    <input className="form-control" type="email" name="email" value={data.email} onChange={handleInputChange} placeholder="Email" required />
                                </div>
                                <div className="form-group" >
                                    <input className="form-control" type="password" name="password" value={data.password} onChange={handleInputChange} placeholder="Password" required />
                                </div>
                                <div className="form-group" >
                                    <input className="form-control" type="text" name="image" value={data.image} onChange={handleInputChange} placeholder="Image" required />
                                </div>
                                <div className="button">
                                    <button type="submit" className="btn btn-primary btn-lg btn-block">Registrarte</button>
                                </div>
                            </div>
                        </form>
                </div>
            </div>
        </div>
                            
    )
}

export default Register ;