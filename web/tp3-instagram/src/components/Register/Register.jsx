import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import Notifications,{notify} from 'react-notify-toast';
import axios from "axios";

const Register = () => {
    const history = useHistory();
    const [data, setData] = useState({
        username: "",
        email: "",
        password: "",
        passwordCheck: ""
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
        <div className="input">
            <input type="text" id="username" name="username" placeholder="Username" required/>
            <input type="text" id="email" name="email" placeholder="Email" required/>
            <input type="text" id="password" name="password" placeholder="Password" required/>
            <input type="text" id="passwordCheck" name="passwordCheck" placeholder="Password Check" required/>
            <button type="button">Register</button>
            <button type="button">Cancel</button>
        </div>
    )
}

export default Register ;