import React, { useState, useEffect } from "react";
import Navbar from '../Navbar/Navbar'
import axios from "axios";

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
                    <div className="posts col-md-3">
                    </div>
                    <div className="posts col-md-5">
                        {user.timeline.map(post =>  (
                            <div className="post">
                                <div className="imageUserPost">
                                    <img src={post.user.image}/>
                                </div>
                                <div className="nameUserPost">
                                    <p>{post.user.name}</p>
                                </div>
                                <div className="imageUserPost">
                                    <img src={post.portrait}/>
                                </div>
                                <div className="likeUserPost">
                                    <p>{post.likes.length}</p>
                                </div>
                                <div className="descpUserPost">
                                    <p>{post.description}</p>
                                </div>
                            </div>
                        ))}
                    </div>      
                    <div className="posts col-md-4">
                            <div className="userName">
                                <p>{user.name}</p>
                            </div>
                            <div className="imagenUserName">
                                <img src={user.image}/>
                            </div>
                            <div className="followers">
                                <h5>Followers</h5>
                                {user.followers.map(follower => (
                                    <ul>
                                        <img src={follower.image}/>
                                        <p>{follower.name}</p>
                                    </ul>
                                ))}
                            </div>
                    </div>
                </div>
            </div>
        </div>
        
    )
}


export default Home ;
        /*
            */