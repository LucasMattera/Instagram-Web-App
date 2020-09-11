package window

import model.UserModel
import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
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

    }
}