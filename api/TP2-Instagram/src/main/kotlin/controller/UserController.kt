package controller

import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.User
import token.TokenController

data class UserLoginDTO(val email: String, val password: String)

/* atributos originales de user
    id: kotlin.String,
    name: kotlin.String,
    email: kotlin.String,
    password: kotlin.String,
    image: kotlin.String,
    followers: kotlin.collections.MutableList<org.unq.ui.model.User>
 */

class UserDTO(user : User) {
    val id = user.id ;
    val name = user.name ;
    val email = user.email ;
}

class UserController(private val instagramSystem : InstagramSystem){

    val tknCtrl = TokenController()

    fun login(ctx: Context) {
        val userLogin = ctx.body<UserLoginDTO>()  //aca se matchea el dataclass con el json que se escribe en postman
        try {
            val user = instagramSystem.login(userLogin.email, userLogin.password)
            ctx.header("Authorization", tknCtrl.generateToken(user))
            ctx.json(UserDTO(user))
        } catch(e: NotFound){
            throw BadRequestResponse()
        }
    }

}












