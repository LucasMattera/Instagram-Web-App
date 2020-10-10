package model

import org.uqbar.commons.model.annotations.Observable

@Observable
class EditUserModel(var name: String, var password: String, var passwordCheck: String, var image: String) {

    fun nameIsEmpty(): Boolean {
        return  name == ""
    }
    fun passwordIsEmpty(): Boolean {
        return  password == ""
    }
    fun imageIsEmpty(): Boolean {
        return  image == ""
    }
    fun passwordCheckIsEmpty(): Boolean {
        return  passwordCheck == ""
    }

    fun passwordsDontMatch(): Boolean {
        return  password != passwordCheck
    }
}