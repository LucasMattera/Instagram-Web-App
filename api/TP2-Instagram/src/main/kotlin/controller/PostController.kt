package controller

import io.javalin.http.BadRequestResponse
import org.unq.ui.model.InstagramSystem
import io.javalin.http.Context
import org.unq.ui.model.DraftComment
import org.unq.ui.model.NotFound
import token.TokenController
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
                   val likes: MutableList<UserPostDTO>,
                   val user: UserPostDTO,
                   val comments: MutableList<CommentDTO>
)



class PostController(private val instagramSystem : InstagramSystem) {


    private fun getUserId(ctx: Context): String {
        return ctx.attribute<String>("userId") ?: throw BadRequestResponse("Not found user")
    }

    fun getPostById(ctx: Context) {
        val postId = ctx.pathParam("id")
        try {
            val post = instagramSystem.getPost(postId)
            var likesPost = post.likes.map {
                UserPostDTO(it.name, it.image) }.toMutableList()
            var commentPost = post.comments.map {
                CommentDTO(it.id, it.body, UserPostDTO(post.user.name,post.user.image))}.toMutableList()
            var userPost = UserPostDTO(post.user.name,post.user.image)

            ctx.json(
                PostDTO(postId,post.description,post.portrait,post.landscape,post.date,likesPost,userPost,commentPost))
        } catch (e: NotFound ) {
            ctx.status(404)
            ctx.json(
                    mapOf("result" to "Not found post with : $postId")
            )
        }

    }


    fun likePost(ctx: Context) {
        val userId = getUserId(ctx)
        val postId = ctx.pathParam("id")
        try {
            instagramSystem.updateLike(postId, userId)
            ctx.json(OkResponse())
        } catch (e: NotFound) {
            ctx.status(404).json(ErrorResponse("not found post with id $postId"))
        }
    }


    fun commentPost(ctx: Context) {

        val user = getUserId(ctx)
        val postId = ctx.pathParam("id")
        val comment = ctx.body<DraftComment>()
        try {
            instagramSystem.addComment(postId, user, comment)
            ctx.status(200)
            ctx.json(OkResponse())
        } catch (e: NotFound) {
            ctx.status(404).json(ErrorResponse("not found post with id $postId"))
        }
    }
}