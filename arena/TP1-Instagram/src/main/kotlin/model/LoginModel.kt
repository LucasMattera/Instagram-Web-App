import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class LoginModel (val system: InstagramSystem) {
    var email : String = ""
    var password : String = ""
}