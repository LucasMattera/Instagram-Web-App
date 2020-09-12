package window

import LoginModel
import model.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException
import org.unq.ui.model.NotFound

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
                    var user = modelObject.login(modelObject.email,modelObject.password)
                    var model = UserModel(user, modelObject.system)
                    thisWindow.close() ; UserWindow(thisWindow, model).open()
                } catch (e : NotFound ) {
                    throw UserException(e.message)
                }
            }
        }
    }

}