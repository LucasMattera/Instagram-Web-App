package controller

import dto.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem


class InstagramController(private val instagramSystem : InstagramSystem) {


    fun getSearchContent(ctx : Context) {
        val textToSearch = ctx.queryParam("q") ?: throw BadRequestResponse("Invalid query - param q is null")

        try {
            if (textToSearch.first() == '#') {
                val postDescription = instagramSystem.searchByTag(textToSearch).map {
                    val likes = it.likes.map {
                        UserPostDTO(it.name, it.image)
                    }.toMutableList()
                    val userPost = UserPostDTO(it.user.name, it.user.image)

                    PostUserDTO(it.id, it.description, it.portrait, it.landscape, it.date, likes, userPost)
                }.toMutableList()
                ctx.json(
                    DescriptionPostDTO(postDescription)
                )
            } else {
                val postUser = instagramSystem.searchByName(textToSearch).map {
                    val followersUser = it.followers.map {
                        UserPostDTO(it.name, it.image)
                    }.toMutableList()
                    UserSearchDTO(it.name, it.image, followersUser)
                }.toMutableList()
                ctx.json(
                    UserByNameDTO(postUser)
                )
            }
        } catch (e : Exception) {
            ctx.status(404)
            ctx.json(
                mapOf("result" to "error",
                    "message" to e.message)
            )
        }


    }

}