package window

import model.EditUserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class EditUserWindow(owner: WindowOwner, model: EditUserModel) : Dialog<EditUserModel>(owner,model) {

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

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Profile"

        labelText(mainPanel,"Name")
        textBox(mainPanel,"name")

        labelText(mainPanel,"Password")
        textBox(mainPanel,"password")

        labelText(mainPanel,"Image")
        textBox(mainPanel,"image")

        Button(mainPanel) with {
            text = "Accept"
            onClick {
                accept()
            }
        }

        Button(mainPanel) with {
            text = "Cancel"
            onClick {
                cancel()
            }
        }
    }
}