package controller

import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.User
import token.TokenController

data class UserLoginAdapter(val email: String, val password: String)

/* atributos originales de user
    id: kotlin.String,
    name: kotlin.String,
    email: kotlin.String,
    password: kotlin.String,
    image: kotlin.String,
    followers: kotlin.collections.MutableList<org.unq.ui.model.User>
 */
class UserDTO(usr: User){
    val id = usr.id
    val name = usr.name
    val email = usr.email
    val password = usr.password
    val image = usr.image
}

class UserController(private val instagramSystem : InstagramSystem){

    val tknCtrl = TokenController()

    fun loginUser(ctx: Context) {
        val usrLogin = ctx.body<UserDTO>()  //aca se matchea el dataclass con el json que se escribe en postman
        try {
            val usr = instagramSystem.login(usrLogin.email, usrLogin.password)
            ctx.header("Authorization", tknCtrl.generateToken(usr))
            ctx.json(UserDTO(usr))
        } catch(e: NotFound){
            throw BadRequestResponse()
        }
    }

}












