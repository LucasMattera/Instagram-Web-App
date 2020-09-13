package window

import model.DraftPostModel
import model.PostModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class EditPostWindow(owner: WindowOwner, model: DraftPostModel) : Dialog<DraftPostModel>(owner,model) {


    override fun createFormPanel(mainPanel: Panel) {
        title = "Edit Post"

        Label(mainPanel) with {
            text = "Portrait"
        }
        TextBox(mainPanel) with {
            bindTo(propertyName = "portrait")
        }

        Label(mainPanel) with {
            text = "Landscape"
        }
        TextBox(mainPanel) with {
            bindTo(propertyName = "landscape")
        }
        Label(mainPanel) with {
            text = "Description"
        }
        TextBox(mainPanel) with {
            bindTo(propertyName = "description")
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