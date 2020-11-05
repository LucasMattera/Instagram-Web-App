import React, { useState } from "react";

const Login = () => {
    const [email,setEmail] = useState("") ;

    return (
        <div className="input">
            <input type="text" id="email" name="email" placeholder="Email" required/>
            <input type="text" id="password" name="password" placeholder="Password" required/>
            <button type="button">Sign in</button>
        </div>
    )
}

export default Login ;