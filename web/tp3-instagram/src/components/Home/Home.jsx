import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Navbar from '../Navbar/Navbar'
import '../../styles/Home.css';
import '../../api/api'
import Api from "../../api/api";


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

    function likePost(post) {
        Api.userLike(post.id)
    }


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
                                <form onSubmit={() => likePost(post)}>
                                    <button type="text" className="btn btn-link">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                                        </svg>    
                                    </button>
                                </form>
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