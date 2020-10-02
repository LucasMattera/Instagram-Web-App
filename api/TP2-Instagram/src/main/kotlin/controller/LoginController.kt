package controller

import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User

data class UserLogin(val email: String, val password: String )

class LoginController(private val instagramSystem : InstagramSystem) {

    fun loginUser(ctx: Context) {
        val usr = ctx.body<UserLogin>()  //aca se matchea el dataclass con el json que se escribe en postman
        ctx.json(instagramSystem.login(usr.email, usr.password))
    }

}