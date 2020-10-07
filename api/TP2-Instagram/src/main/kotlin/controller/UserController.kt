package controller

import io.javalin.http.Context
import javafx.animation.Timeline
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.UsedEmail
import token.TokenController
import java.time.LocalDateTime

data class UserLoginDTO(val email: String, val password: String)
data class UserRegisterDTO(val name:String,val email:String,val password: String,val image:String)
data class PostUserDTO(val id: String,
                       val description: String,
                       val portrait: String,
                       val landscape: String,
                       val date: LocalDateTime,
                       val likes: MutableList<UserPostDTO>,
                       val user: UserPostDTO)

data class UserGetDTO(val name:String,
                      val image:String,
                      val followers: MutableList<UserPostDTO>,
                      val posts: MutableList<PostUserDTO>)

data class UserDTO (
    val name: String,
    val image: String,
    val followers: MutableList<UserPostDTO>,
    val timeline : MutableList<PostUserDTO>

)

class UserController(private val instagramSystem : InstagramSystem){

    val tokenJWT = TokenController()

    fun login(ctx: Context) {
        val userLogin = ctx.body<UserLoginDTO>()
        try {
            val user = instagramSystem.login(userLogin.email, userLogin.password)
            ctx.header("Authorization", tokenJWT.generateToken(user))
            ctx.status(200)
            ctx.json(
                    mapOf("result" to "ok")
            )
        } catch(e: NotFound){
            ctx.status(404)
            ctx.json(
                    mapOf("result" to "error",
                          "message" to e.message)
            )
        }
    }


    fun register(ctx: Context) {
        val userRegister = ctx.body<UserRegisterDTO>()
        try {
            val user = instagramSystem.register(userRegister.name, userRegister.email, userRegister.password, userRegister.image)
            ctx.header("Authorization", tokenJWT.generateToken(user))
            ctx.status(201)
            ctx.json(
                    mapOf("result" to "ok")
            )
        } catch (e: UsedEmail) {
            ctx.status(404)
            ctx.json(
                    mapOf("result" to "error",
                          "message" to e.message)
            )
        }
    }


    fun getUserById(ctx: Context) {
        val userId = ctx.pathParam("id")
        try {
            val user = instagramSystem.getUser(userId)
            val posts = instagramSystem.searchByUserId(userId)
            var userPost = UserPostDTO(user.name,user.image)
            val followersUser = user.followers.map {
                UserPostDTO(it.name,it.image) }.toMutableList()
            val postsUser = posts.map {
                val likes = it.likes.map {
                    UserPostDTO(it.name,it.image)}.toMutableList()
                PostUserDTO(it.id,it.description,it.portrait,it.landscape,it.date,likes,userPost)}.toMutableList()
            ctx.json(
                UserGetDTO(user.name, user.image, followersUser, postsUser)
            )
        } catch (e: NotFound ) {
            ctx.status(404)
            ctx.json(
                mapOf("result" to "Not found post with : $userId")
            )
        }
    }


    fun followerUser(ctx: Context) {
        val token = ctx.header("Authorization")
        val toUserId = tokenJWT.validateToken(token!!)
        val fromUser = ctx.pathParam("id")
        try {
            instagramSystem.updateFollower(fromUser,toUserId)
            ctx.status(200)
            ctx.json(
                mapOf(
                    "result" to "ok"
                )
            )
        } catch (e: NotFound) {
        ctx.status(404)
        ctx.json(
            mapOf("result" to "Not found user with : $fromUser")
            )
        }
    }

    fun getUser (ctx: Context){
        val token = ctx.header("Authorization")

        try {
            val userId = tokenJWT.validateToken(token!!)
            val user = instagramSystem.getUser(userId)
            var userPost = UserPostDTO(user.name,user.image)

            val userTimeline = instagramSystem.timeline(userId).map {
                val likes = it.likes.map {
                    UserPostDTO(it.name,it.image)}.toMutableList()
                PostUserDTO(it.id, it.description, it.portrait, it.landscape, it.date, likes, userPost)
            }.toMutableList()

            val followersUser = user.followers.map {
                UserPostDTO(it.name,it.image) }.toMutableList()


            ctx.status(200)
            ctx.json(
                    UserDTO(user.name, user.image, followersUser ,userTimeline)
            )
        } catch (e:NotFound){
            ctx.status(404).json(e.message!!)
        }
    }
}













