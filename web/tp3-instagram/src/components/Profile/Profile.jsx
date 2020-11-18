import React, { useState, useEffect} from "react";
import Navbar from '../Navbar/Navbar';
import { Link } from "react-router-dom";
import '../../styles/Profile.css';
import Api from "../../api/api";

const Profile = () => {
    const [user,setUser] = useState({
        id: "",
        name: "",
        image: "",
    })
    const [posts,setPosts] = useState([])

    function getUserId(id){
        Api.getUserById(id)
        .then(success=>{
            setPosts(success.data.posts)
        }).catch(error => {
            console.log(error) 
        });
    }


    useEffect(() => {    
            Api.getUser()
            .then(success =>{ 
               setUser({id:success.data.id,
                        name:success.data.name,
                        image:success.data.image})         

                getUserId(success.data.id)       
            })
            .catch(error =>
                console.log(error)
            )              
    }, []
    )

    
    console.log(user)

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
            </div>
            
            <div className="container-fluid">
                <div className="row">
                    {posts.map(post => (
                        <div className="card col-md-4 col-sm-12">
                            <div className="imageUserPost">
                                <Link to={`/post/${post.id}`}>
                                    <img className="imageUserPost" src={post.landscape}/>
                                </Link>
                            </div>
                            
                        </div>    
                    ))}
                </div>
            </div>
        </div>    

    )
}

export default Profile ;
