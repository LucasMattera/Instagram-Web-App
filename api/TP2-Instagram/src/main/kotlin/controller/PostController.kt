package controller

import org.unq.ui.model.InstagramSystem
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import org.unq.ui.model.NotFound

class PostController(private val instagramSystem : InstagramSystem) {

    fun getPost(ctx: Context) {
        val postId = ctx.pathParam("id")
        try {
            ctx.json(instagramSystem.getPost(postId))
        } catch (e: NotFound) {
            throw NotFoundResponse(e.message!!)
        }
    }

    fun getPosts(ctx: Context) {
        ctx.json(instagramSystem.posts)
    }
}