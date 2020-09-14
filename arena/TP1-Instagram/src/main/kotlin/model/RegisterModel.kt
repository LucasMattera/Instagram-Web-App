package model

import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class RegisterModel(val system: InstagramSystem) {
    var name : String = ""
    var email : String = ""
    var password : String = ""
    var passwordCheck : String = ""
    var image : String = ""

    fun signIn(name : String, email : String, pass : String, image : String){
        system.register(name, email, pass, image)
    }
}
