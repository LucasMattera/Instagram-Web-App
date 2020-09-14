package window

import model.AddPostModel
import model.DraftPostModel
import model.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class AddPostWindow (owner: WindowOwner, model: UserModel) : Dialog<UserModel>(owner, model){
    override fun addActions(actionsPanel: Panel) {
        Button(actionsPanel) with {
            caption = "Agregar Post"
            onClick {
                val post = DraftPostModel()
                val view = EditPostWindow(thisWindow, post)
                view.onAccept(
                    modelObject.addPost(post)
                )
                view.open()

            }
        }
    }
    override fun createFormPanel(mainPanel: Panel) {
        title = "Agregar Post"
    }

}