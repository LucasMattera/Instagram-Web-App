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

    override fun addActions(actionPanel: Panel) {
    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "Instagram"

        Label(mainPanel) with {
            text = "Email"
            alignLeft()
        }
        TextBox(mainPanel) with {
            bindTo(propertyName = "email")
            width = 150
        }
        Label(mainPanel) with {
            alignLeft()
            text = "Password"
        }
        PasswordField(mainPanel) with {
            bindTo(propertyName = "password")
        }

        Button(mainPanel) with {
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