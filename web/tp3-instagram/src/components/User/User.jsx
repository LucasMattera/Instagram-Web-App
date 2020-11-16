import React, { useState, useEffect} from "react";
import {useParams} from 'react-router-dom';
import Navbar from '../Navbar/Navbar'
import axios from "axios";
import Api from "../../api/api";
import '../../styles/Profile.css';

const User = () => {
    const {id} = useParams()
    const [user,setUser] = useState({
        name: "",
        image: "",
        followers: [],
        posts: []
    })
    const [userLogued,setUserLogued] = useState({
        name:"",
        image:""
    })

    function getUserLogued() {
        Api.getUser()
            .then(success => {
                setUserLogued({name:success.data.name,image:success.data.image})
            })
    }

     
    useEffect(() => {    
        Api.getUserById(id)
            .then(success=>{
                setUser(success.data)
            }).catch(error => {
                console.log(error) 
            });
            getUserLogued()          
        }, []
    )

    return(
        <div>
            <Navbar />
            <div className="user text-center">
                <div className="imagee">
                    <img className="image_Post" src={user.image}/>
                </div>
                <div className="nameUserPost">
                    <p>{user.name}</p>
                </div>
                    <button type="submit" className="btn btn-link" onClick=
                    {() => Api.userFollow(id)}>{user.followers.find(u => u.name === userLogued.name) ? 'unFollow' : 'Follow'}</button>
                </div>
            
            <div className="container-fluid">
                <div className="row">
                    {user.posts.map(post => (
                        <div className="card col-md-4 col-sm-12">
                            <div className="imageUserPost">
                                <img className="imageUserPost" src={post.landscape}/>
                            </div>
                        </div>    
                    ))}
                </div>
            </div>
        </div>    

    )
}

export default User ;

