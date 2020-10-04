package controller

import org.unq.ui.model.InstagramSystem
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import org.unq.ui.model.Comment
import org.unq.ui.model.NotFound
import java.time.LocalDateTime


data class UserPostDTO(val name: String, val image: String)
data class CommentDTO(val id: String, val body: String, val user: UserPostDTO)
data class PostDTO(val id: String,
                   val description: String,
                   val portrait: String,
                   val landscape: String,
                   val date: LocalDateTime,
                   val likes: List<UserPostDTO>,
                   //val user: UserPostDTO,
                   //val comments: MutableList<CommentDTO>
    )




class PostController(private val instagramSystem : InstagramSystem) {


    fun getPost(ctx: Context) {
        val postId = ctx.pathParam("id")
        val post = instagramSystem.getPost(postId)
        var likesPost = post.likes.map {
            UserPostDTO(it.name, it.image)
        }
        try {
            ctx.json(
                PostDTO(postId,post.description,post.portrait,post.landscape,post.date,likesPost))
        } catch (e: NotFound) {
            ctx.status(404).json(NotFoundResponse(e.message!!))
        }
    }

}