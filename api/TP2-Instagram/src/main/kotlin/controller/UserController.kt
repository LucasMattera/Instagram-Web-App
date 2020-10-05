package controller

import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.UsedEmail
import org.unq.ui.model.User
import token.TokenController

data class UserLoginDTO(val email: String, val password: String)
data class UserRegisterDTO(val name:String,val email:String,val password: String,val image:String)


class UserController(private val instagramSystem : InstagramSystem){

    val token = TokenController()

    fun login(ctx: Context) {
        val userLogin = ctx.body<UserLoginDTO>()
        try {
            val user = instagramSystem.login(userLogin.email, userLogin.password)
            ctx.header("Authorization", token.generateToken(user))
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
            ctx.header("Authorization", token.generateToken(user))
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
        val user = instagramSystem.getUser(userId)

    }

}












