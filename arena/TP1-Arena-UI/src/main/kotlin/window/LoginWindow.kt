package org.example

import LoginModel
import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.User
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class LoginWindow : SimpleWindow<LoginModel> {
    constructor(owner: WindowOwner, model: LoginModel) : super(owner, model)

    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(p0: Panel?) {
        title = "Instagram"

        Label(p0) with {
            text = "Email"
            alignLeft()
        }
        TextBox(p0) with {
            bindTo(propertyName = "email")
            width = 150
        }
        Label(p0) with {
            alignLeft()
            text = "Password"
        }
        PasswordField(p0) with {
            bindTo(propertyName = "password")
        }
        
        Button(p0) with {
            caption = "Iniciar Sesion"
            onClick { autenticar(modelObject.email,modelObject.password) }
        }
    }

    private fun autenticar(email : String,password : String) : User {
        return modelObject.system.login(email,password)

    }
}