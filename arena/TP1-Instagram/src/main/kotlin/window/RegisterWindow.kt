package window

import model.RegisterModel
import org.uqbar.arena.kotlin.extensions.bindTo
import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.thisWindow
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class RegisterWindow(owner: WindowOwner, model: RegisterModel) : Dialog<RegisterModel>(owner, model) {

    fun textBox(panel: Panel, propiedad: String){
        TextBox(panel) with {
            bindTo(propiedad)
        }
    }
    fun labelText(panel:Panel ,texto: String ){
        Label(panel) with {
            text = texto
        }
    }

    override fun addActions(actionPanel: Panel) {
        Button(actionPanel) with {
            text = "Register"
            onClick {
                accept()
            }
        }
        Button(actionPanel) with {
            text = "Cancel"
            onClick {
                cancel()
            }
        }

    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "Create User"

        labelText(mainPanel,"Name: ")
        textBox(mainPanel,"name")

        labelText(mainPanel,"Email: ")
        textBox(mainPanel,"email")

        labelText(mainPanel,"Password: ")
        textBox(mainPanel,"password")

        labelText(mainPanel,"Repeat Password: ")
        textBox(mainPanel,"passwordCheck")


    }

}

