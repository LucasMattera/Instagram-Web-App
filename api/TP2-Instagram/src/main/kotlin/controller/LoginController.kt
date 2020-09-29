package controller

import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem

class LoginController(private val instagramSystem : InstagramSystem) {

    fun createLogin(ctx: Context, email: String, psw: String) {
        ctx.json(instagramSystem.login(email, psw))
    }
}