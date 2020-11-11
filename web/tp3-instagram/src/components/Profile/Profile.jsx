import React, { useState, useEffect} from "react";
import {useParams} from 'react-router-dom';
import Navbar from '../Navbar/Navbar'
import axios from "axios";

const Profile = () => {
    const { id } = useParams();
    const [user,setUser] = useState({
        name: "",
        image: "",
        posts: []
    })

    useEffect(() => {
            axios.get("http://localhost:7000/user/"+id)
            .then(success =>{               
                setUser(success.data)
                
            })
            .catch(error =>
                console.log(error)
            )            
    }, []
    )

    console.log(user)

    return(
        <div className="home">
            <Navbar />
            <h1>Hola</h1>
        </div>
    )
}

export default Profile ;
