package window

import model.PostModel
import model.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action



class UserWindow : SimpleWindow<UserModel> {

    constructor(owner: WindowOwner, model: UserModel) : super(owner, model)

    override fun addActions(actionPanel: Panel) {
        Button(actionPanel) with {
            caption = "Add Post"
        }
        Button(actionPanel) with {
            caption = "Edit Post"
        }
        Button(actionPanel) with {
            caption = "Remove Post"
        }


    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "User View"

        Label(mainPanel) with {
            text = "id : ${modelObject.id}"
            alignLeft()
        }
        Label(mainPanel) with {
            text = "name : ${modelObject.name}"
            alignLeft()
        }
        Label(mainPanel) with {
            text = "email : ${modelObject.email}"
            alignLeft()
        }
        Button(mainPanel) with {
            text = "Edit Profile"
        }
        Label(mainPanel) with {
            text = "-----------------------------------------------------------------------------------------------------------------------------------------------------------"
        }
        TextBox(mainPanel) with {
            bindTo(propertyName = "description")
            width=300
        }
        Button(mainPanel) with {
            caption = "Search"
            onClick( Action { modelObject.filterByDescription(modelObject.description) } )
        }
        Button(mainPanel) with {
            caption = "Back"
            onClick ( Action {
                modelObject.resetPost()
            })
        }

        table<PostModel>(mainPanel) {
            bindItemsTo("posts")
            bindSelectionTo("selected")
            visibleRows = 15

            column {
                title = "#"
                bindContentsTo("id")
                fixedSize = 50
            }
            column {
                title = "Description"
                bindContentsTo("description")
                fixedSize = 250
            }
            column {
                title = "Landscape"
                bindContentsTo("landscape")
                fixedSize = 200
            }
            column {
                title = "Portrait"
                bindContentsTo("portrait")
                fixedSize = 200
            }
        }
    }
}