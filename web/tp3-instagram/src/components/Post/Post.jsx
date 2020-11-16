import React, { useState, useEffect} from "react";
import {useParams} from 'react-router-dom';
import Navbar from '../Navbar/Navbar'
import Api from "../../api/api";
import '../../styles/Post.css';

const Post = () => {
    const {id} = useParams()
    const [post,setPost] = useState({
        landscape: "",
        likes: [],
        comments: []
    })

    const [user,setUser] = useState({
        name:"",
        image:"",
    })

    function getUserLogued() {
        Api.getUser()
            .then(success => {
                setUser({name:success.data.name,image:success.data.image})
            })
            .catch(error => {
                console.log(error)
            })
    }

    function getPostById(id) {
        Api.getPostById(id)
            .then(success => {
                setPost({landscape:success.data.landscape,likes:success.data.likes,comments:success.data.comments})
            })
            .catch(error => {
                console.log(error)
            })
    }

    useEffect(() => { 
        getUserLogued()      
        getPostById(id)
    },[])


    return(
        <div className="post">
        <Navbar />
            <div className="container-fluid">
                <div className="row">
                    <div className="posts-izquierda col-md-3 col-sm-12"></div>
                    <div className="post-medio col-md-6 col-sm-12">
                        <div className="postData">
                            <div className="image">
                                <img className="imagePostt" src={post.landscape} />
                            </div>
                            <div className="like">
                                <p>{post.likes.length} Me gusta</p>
                            </div>
                            <hr/>
                        </div>
                            
                        {post.comments.map(comment => (
                            <div className="comment">
                                <div className="imageUserComment">
                                    <img className="imageUser" src={comment.user.image}/>
                                </div>
                                <div className="nameUserComment">
                                    <p>{comment.user.name}</p>
                                </div>
                                <div className="commentBody">
                                    <p>{comment.body}</p>
                                </div>
                                <hr/>
                            </div>
                        ))}
                        
                    </div>      
                    <div className="posts-derecha col-md-3 col-sm-12"></div>
                </div>
            </div>
        </div>
    )

}

export default Post ;