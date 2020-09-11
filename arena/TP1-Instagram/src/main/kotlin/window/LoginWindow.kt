package window

import LoginModel
import model.UserModel
import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.User
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class LoginWindow : SimpleWindow<LoginModel> {
    constructor(owner: WindowOwner, model: LoginModel) : super(owner, model)

    override fun addActions(p0: Panel) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "Instagram"

        Label(p0) with {
            text = "Email"
            alignLeft()
        }
        TextBox(p0) with {
            bindTo(propertyName = "email")
            width = 150
        }
        Label(p0) with {
            alignLeft()
            text = "Password"
        }
        PasswordField(p0) with {
            bindTo(propertyName = "password")
        }

        Button(p0) with {
            caption = "Login"
            onClick {
                try {
                    var user = autenticate(modelObject.email, modelObject.password)
                    var system = getInstagramSystem() //creo que aca es donde jota dijo que estamos instanciando un nuevo ig system
                    var model = UserModel(user, system)
                    thisWindow.close(); UserWindow(thisWindow, model).open()
                } catch (e : Exception) { // aca mepa que hay que cambiarlo por UserException
                    throw UserException("Email o password incorrectos !")
                }
            }
        }
    }


    private fun autenticate(email: String, password: String): User {
        return modelObject.system.login(email, password)
    }
}