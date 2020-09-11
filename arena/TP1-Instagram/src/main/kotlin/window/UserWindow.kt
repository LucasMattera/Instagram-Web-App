package window

import model.PostModel
import model.UserModel
import org.unq.ui.model.User
import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.Post
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner


class UserWindow : SimpleWindow<UserModel> {

    constructor(owner: WindowOwner, model: UserModel) : super(owner, model)

    override fun addActions(p0: Panel) {
    }

    override fun createFormPanel(p0: Panel) {
        title = "User View"
        Label(p0) with {
            text = "id : ${modelObject.id}"
            alignLeft()
        }
        Label(p0) with {
            text = "name : ${modelObject.name}"
            alignLeft()
        }
        Label(p0) with {
            text = "email : ${modelObject.email}"
            alignLeft()
        }
        Button(p0) with {
            text = "Edit profile"
            width = 300
        }
        table<PostModel>(p0) {
            bindItemsTo("posts")
            bindSelectionTo("selected")

            column {
                title = "#"
                bindContentsTo("id")
            }
            column {
                title = "description"
                bindContentsTo("description")
            }
        }
/*
    private fun filterById(id: String): Post {
        return modelObject.system.getPost(id)
    }

 */
    }
}