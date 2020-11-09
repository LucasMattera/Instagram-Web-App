import React, { useState, useEffect } from "react";
import Navbar from '../Navbar/Navbar'
import Post from '../Post/Post';
import axios from "axios";

const Home = () => {
    const [timeline, setTimeline] = useState([]) ;

    useEffect(() => {
        const getUserTimeline = async () =>{
            axios.get("http://localhost:7000/user")
            .then(response =>{
                setTimeline(response.data.timeline)
            })
        }
        getUserTimeline();
    }, []
    )

    console.log(timeline)
   
    return (
        <div className="home">
            <Navbar />
            <div className="contenedor-posts">
                    {timeline.map(post => (
                        <div className="container">
                            <div className="posts">
                                <div className="imageUserPost">
                                    <p>{post.user.image}</p>
                                </div>
                                <div className="nameUserPost">
                                    <p>{post.user.name}</p>
                                </div>
                                <div className="imageUserPost">
                                    <p>{post.landscape}</p>
                                </div>
                                <div className="likeUserPost">
                                    <p>{post.likes.length}</p>
                                </div>
                                <div className="descpUserPost">
                                    <p>{post.description}</p>
                                </div>
                            </div>
                        </div>
                    ))}
            </div>
        </div>
    )
}


export default Home ;