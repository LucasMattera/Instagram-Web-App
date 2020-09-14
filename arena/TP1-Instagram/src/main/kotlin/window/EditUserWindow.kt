package window

import model.EditUserModel
import model.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class EditUserWindow(owner: WindowOwner, model: EditUserModel) : Dialog<EditUserModel>(owner,model) {

    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit User"

        Label(mainPanel) with {
            text = "Name"
        }
        TextBox(mainPanel) with {
            bindTo(propertyName = "name")
        }
        Label(mainPanel) with {
            text = "Password"
        }
        TextBox(mainPanel) with {
            bindTo(propertyName = "password")
        }
        Label(mainPanel) with {
            text = "Image"
        }
        TextBox(mainPanel) with {
            bindTo(propertyName = "image")
        }
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