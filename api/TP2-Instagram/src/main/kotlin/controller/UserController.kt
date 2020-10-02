package controller

import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem

data class UserLoginAdapter(val email: String, val password: String )

class UserController(private val instagramSystem : InstagramSystem) {

    fun loginUser(ctx: Context) {
        val usr = ctx.body<UserLoginAdapter>()  //aca se matchea el dataclass con el json que se escribe en postman
        ctx.json(instagramSystem.login(usr.email, usr.password))
    }

}