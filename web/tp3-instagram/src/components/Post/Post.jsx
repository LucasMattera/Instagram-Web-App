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
    })
    const [comments,setComments] = useState([])

    const [comment,setComment] = useState("")

    const handleInputChange = (event) => {
        setComment(event.target.value)
    }

    const addComment = (event) => {
        event.preventDefault()
        const token = localStorage.getItem("token")
        const data = {
            body: comment,
        };
        const headers = {
            authorization: token,
          };
        Api.postComment(id,data,{headers:headers})
            .then((response) =>
                setComments(response.data)
            )
            .catch((error) => console.log(error)
        ) 
    }

    function getPostById(id) {
        Api.getPostById(id)
            .then(success => {
                setPost({landscape:success.data.landscape,likes:success.data.likes})
                setComments(success.data.comments)
            })
            .catch(error => {
                console.log(error)
            })
    }

    useEffect(() => {      
        getPostById(id)
    },[])
    

    console.log(comment)

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
                            <div className="iconMg">
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-heart-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                                </svg>
                            </div>
                            <div className="like">
                                <p>{post.likes.length} Me gusta</p>
                            </div>
                            
                            <hr/>
                        </div>
                            
                        {comments.map(comment => (
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
                        <form onSubmit={addComment}>
                            <input className="form-control" type="text" name="body" value={comment} onChange={handleInputChange} placeholder="Publicar"/>
                            <div className="button">
                                <button type="text" className="">Agregar</button>
                            </div>       
                        </form>        
                    </div>      
                    <div className="posts-derecha col-md-3 col-sm-12"></div>
                </div>
            </div>
        </div>
    )

}

export default Post ;