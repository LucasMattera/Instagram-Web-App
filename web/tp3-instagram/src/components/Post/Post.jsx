import React from 'react' ;

const Post = ({post}) => {
    
    const {id, user, portrait, likes, description} = post;
    return(
        <div className="container">
            
            <div className="post">
                <p className="text-post">{post.user}</p>
                <p className="text-post">{post.portrait}</p>
                <p className="text-post">{post.likes}</p>
                <p className="text-post">{post.description}</p>
            </div>
        </div>
    )
}

export default Post ;