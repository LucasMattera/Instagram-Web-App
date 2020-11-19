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

    const addLike = (post) => {
        Api.userLike(post.id)
        .then(success =>
            setUser(prevUser => ({
                ...prevUser,
                timeline: prevUser.timeline.map(p => {
                    if ( p.id == post.id) {
                        return {...post, likes:success.data}
                    }
                    return p
                }) 
            }))
        )
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
                                <button type="text" className="btn btn-link" 
                                    onClick={(event) =>  {event.preventDefault(); addLike(post) }}>{post.likes.find(u => u.name == user.name) 
                                    ?
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                                        </svg>
                                        :
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                                        </svg>} 
                                </button>
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
                                <h5>Following</h5>
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