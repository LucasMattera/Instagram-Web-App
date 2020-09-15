package model

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class RegisterModel(val system: InstagramSystem) {
    var name : String = ""
    var email : String = ""
    var password : String = ""
    var passwordCheck : String = ""
    var image : String = ""

    fun register(name : String, email : String, pass : String,image : String) : User {
        return system.register(name, email, pass, image)
    }

}
