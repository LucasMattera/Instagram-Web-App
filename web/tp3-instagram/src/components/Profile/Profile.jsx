import React, { useState, useEffect} from "react";
import {useParams} from 'react-router-dom';
import Navbar from '../Navbar/Navbar'
import axios from "axios";

const Profile = () => {

    const [user,setUser] = useState({
        id: "",
        name: "",
        image: "",
        posts: [],
    })

    function getUserId(){
        axios.get("http://localhost:7000/user/"+user.id)
        .then(success=>{
            setUser(success.data.posts)
        }).catch(error => {
            console.log(error) 
        });
    }


    useEffect(() => {    
            axios.get("http://localhost:7000/user")
            .then(success =>{ 
                setUser(success.data.id)              
                setUser(success.data.name)
                setUser(success.data.image)
                getUserId()       
            })
            .catch(error =>
                console.log(error)
            )              
    }, []
    )

    
    console.log(user)

    return(
        <div>Hola</div>

    )
}

export default Profile ;

        /*<div className="profile">
            <Navbar />
            <div className="container-fluid">
                <div className="row">
                    <div className="posts col-md-4 col-sm-12">
                        {user.posts.map(post => (
                             
                            <div className="imageUserPost">
                                <img className="imageUserPost" src={post.portrait}/>                              
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
        */