import React, { useState, useEffect } from "react";
import Navbar from '../Navbar/Navbar'
import axios from "axios";
import '../../styles/Home.css';

const Home = () => {
    const [user,setUser] = useState({
        name: "",
        image: "",
        followers: [],
        timeline: [],
    })

    useEffect(() => {
            axios.get("http://localhost:7000/user")
            .then(success =>{               
                setUser(success.data)
                
            })
            .catch(error =>
                console.log(error)
            )            
    }, []
    );
       
    console.log(user)

    return(
        <div className="home">
            <Navbar />
            <div className="container-fluid">
                <div className="row">
                    <div className="posts-izquierda col-md-3 col-sm-12">
                    </div>
                    <div className="posts-medio col-md-5 col-sm-12">
                        {user.timeline.map(post =>  (
                            <div className="post">
                                <div className="imagePost">
                                    <img className="imagePost" src={post.user.image}/>
                                </div>
                                <div className="nameUserPost">
                                    <p>{post.user.name}</p>
                                </div>
                                <div className="imageUserPost">
                                    <img className="imageUserPost" src={post.portrait}/>
                                </div>
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
                            <div className="imagenUserName">
                                <img className="imagenUserName" src={user.image}/>
                            </div>
                            <div className="userName">
                                <p>{user.name}</p>
                            </div>
                            <div className="followers">
                                <h5>Followers</h5>
                                {user.followers.map(follower => (
                                    <div>
                                        <img className="imagenUserName" src={follower.image}/>
                                        <p className="userName">{follower.name}</p>
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