import exception.LoginException
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class LoginModel (val system: InstagramSystem) {
    var email : String = ""
    var password : String = ""

    fun login(email : String,password : String) : User {
        return system.login(email,password)
    }


    fun comprobarDatos(email : String, password : String) {
        if ( this.email != email && this.password != password) {
            throw LoginException("Email o password incorrecto")
        }
    }
}