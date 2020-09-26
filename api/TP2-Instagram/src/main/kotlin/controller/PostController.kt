package controller

import org.unq.ui.model.InstagramSystem
import io.javalin.http.Context

class PostController(private val instagramSystem : InstagramSystem) {

    fun getPost(ctx: Context) {
        val postId = ctx.pathParam("id")
        ctx.json(instagramSystem.getPost(postId))
    }

    fun getPosts(ctx: Context) {
        ctx.json(instagramSystem.posts)
    }
}