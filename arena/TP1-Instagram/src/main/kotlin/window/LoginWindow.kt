package window

import LoginModel
import model.RegisterModel
import model.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException
import org.unq.ui.model.NotFound
import org.unq.ui.model.User

class LoginWindow : SimpleWindow<LoginModel> {
    constructor(owner: WindowOwner, model: LoginModel) : super(owner, model)


    fun textBox(panel: Panel, propiedad: String, widthP : Int){
        TextBox(panel) with {
            width = widthP
            bindTo(propiedad)
        }
    }

    fun labelText(panel:Panel ,texto: String ){
        Label(panel) with {
            text = texto
            alignLeft()
        }
    }

    fun passwordField(panel: Panel, pass : String){
        PasswordField(panel) with {
            bindTo(propertyName = pass)
        }
    }

    override fun addActions(mainPanel: Panel) {}

    override fun createFormPanel(mainPanel: Panel) {
        title = "Instagram"

        labelText(mainPanel,"Email")
        textBox(mainPanel,"email", 250)

        labelText(mainPanel,"Password")
        passwordField(mainPanel, "password")

        fun algunosDeLosCamposEstanVacios(modelObject: LoginModel): Boolean {
            return ( modelObject.email == "" || modelObject.password == "")
        }

        Button(mainPanel) with {
            caption = "Login"
            onClick {

                if ( algunosDeLosCamposEstanVacios (modelObject)) {
                    throw UserException(" The field cannot be empty ")
                }
                try {
                    var user = modelObject.login(modelObject.email,modelObject.password)
                    var model = UserModel(user, modelObject.system)
                    thisWindow.close() ; UserWindow(thisWindow, model).open()
                } catch (e : NotFound ) {
                    throw UserException("Email or password incorrect")
                }
            }
        }

        Button(mainPanel) with {
            caption = "Sign In"
            onClick {
                val model = RegisterModel(modelObject.system)
                RegisterWindow(thisWindow, model).open()
            }
        }
    }
}