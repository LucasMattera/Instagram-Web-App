package window

import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class RegisterWindow(val system: InstagramSystem) {
    var name : String = ""
    var email : String = ""
    var password : String = ""
    var passwordCheck : String = ""
    var image : String = ""
}
