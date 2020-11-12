import React, { useState, useEffect} from "react";
import axios from "axios";

const Like = () => {
    const [user, setUser] = useState({
        name:"",
        image:""
    })
    const [post,setPost] = useState({
        id: ""
    })
    const [likes,setLikes] = useState([])

    function getUser(){
        axios.get("http://localhost:7000/user")
        .then(success =>{ 
            setUser({name:success.data.name,
                    image:success.data.image})         
        })
        .catch(error =>
            console.log(error)
        )              
    }
    
    function getPostId(id){
        axios.get(`http://localhost:7000/post/${id}/like`)
        .then(success=>{
            setLikes(success.data.likes)
        }).catch(error => {
            console.log(error) 
        });
    }

    useEffect(() => {    
            axios.get("http://localhost:7000/post")
            .then(success =>{ 
                setPost({id:success.data.id})
                getUser()
                getPostId(success.data.id)       
            })
            .catch(error =>
                console.log(error)
            )              
    }, []
    )

    return (
        <div>
            
        </div>
    )

}

export default Like;