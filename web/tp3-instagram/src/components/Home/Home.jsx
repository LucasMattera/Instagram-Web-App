import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Navbar from '../Navbar/Navbar'
import axios from "axios";
import '../../styles/Home.css';
import '../../api/api'
import Api from "../../api/api";
import User from '../User/User'

const Home = () => {
    const [user,setUser] = useState({
        id: "",
        name: "",
        image: "",
        followers: [],
        timeline: [],
    })

    useEffect(() => {
            Api.getUser()
            .then(success =>{               
                setUser(success.data)
                
            })
            .catch(error =>
                console.log(error)
            )            
    }, []
    ); 




    return(
        <div className="home">
            <Navbar />
            <div className="container-fluid">
                <div className="row">
                    <div className="posts-izquierda col-md-3 col-sm-12">
                    </div>
                    <div className="posts-medio col-md-5 col-sm-12">
                        {user.timeline.map(post => (
                            <div className="post">
                                {console.log(post)}
                                <div className="imagePost">
                                    <Link to={`/user/${post.user.id}`}>
                                        <img className="imageePost" src={post.user.image}/>
                                    </Link>
                                </div>
                                <div className="nameUserPost">
                                    <p>{post.user.name}</p>
                                </div>
                                <div className="imageUserPost">
                                    <Link to={`/post/${post.id}`}>
                                        <img className="imageUserPost" src={post.portrait}/>
                                    </Link>
                                </div>
                                <button type="submit" className="btn btn-link" onClick=
                                    {() => Api.userLike(post.id)}>Mg</button>
                                <div className="likeUserPost">
                                    <p>{post.likes.length} Me gusta</p>
                                </div>
                                <div className="descpUserPost">
                                    <p>{post.description}</p>
                                </div>
                            </div>
                        ))}
                          
                    </div>      
                    <div className="posts-derecha col-md-4 col-sm-12">
                            <div className="imagenUserNamee">
                                <img className="imagenUserName" src={user.image}/>
                            </div>
                            <div className="userName">
                                <p>{user.name}</p>
                            </div>
                            <div className="followers">
                                <h5>Followers</h5>
                                {user.followers.map(follower => (
                                    <div>
                                        <Link to={`/user/${follower.id}`}>
                                            <img className="imagenUserName" src={follower.image} />
                                        </Link>
                                        <p className="userName">{follower.name} </p>
                                    </div>
                                ))}
                            </div>
                    </div>
                </div>
            </div>
        </div>
        
    )
}


export default Home ;