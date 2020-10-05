package controller

import io.javalin.http.BadRequestResponse
import org.unq.ui.model.InstagramSystem
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import io.javalin.http.UnauthorizedResponse
import org.unq.ui.model.Comment
import org.unq.ui.model.DraftPost
import org.unq.ui.model.NotFound
import java.time.LocalDateTime

data class OkResponse(val status: String = "Ok")
data class ErrorResponse(val message: String)


data class UserPostDTO(val name: String,
                       val image: String)

data class CommentDTO(val id: String,
                      val body: String,
                      val user: UserPostDTO)

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

    private fun getUserId(ctx: Context): String {
        return ctx.attribute<String>("userId") ?: throw BadRequestResponse("Not found user")
    }

    fun getPost(ctx: Context) {
        val postId = ctx.pathParam("id")
        val post = instagramSystem.getPost(postId)
        var likesPost = post.likes.map {
            UserPostDTO(it.name, it.image)
        }
        try {
            ctx.json(
                PostDTO(postId,
                        post.description,
                        post.portrait,
                        post.landscape,
                        post.date,
                        likesPost))
        } catch (e: NotFound) {
            ctx.status(404).json(NotFoundResponse("not found post with id $postId"))
        }
    }

    fun modifyPost(ctx: Context) {
            val userId = getUserId(ctx)
            val postId = ctx.pathParam("id")
        try {
            instagramSystem.updateLike(postId, userId)
            ctx.json(OkResponse())
        } catch (e: NotFound) {
            ctx.status(404).json(ErrorResponse("not found post with id $postId"))
        }
    }
}