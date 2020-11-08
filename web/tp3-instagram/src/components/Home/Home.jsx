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
            
        </div>
    )
}

/*
<div className="contenedor-posts">
                    {timeline.map(post => (
                    <Post key={post.id} post={post}/>
                    ))}
                </div>
                */
export default Home ;