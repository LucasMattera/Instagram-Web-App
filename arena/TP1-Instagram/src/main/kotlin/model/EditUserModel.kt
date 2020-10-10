package model

import org.uqbar.commons.model.annotations.Observable

@Observable
class EditUserModel(var name: String, var password: String, var passwordCheck: String, var image: String) {

    fun someUserFieldsAreEmpty(): Boolean {
        return  (name == "" || password == "" || image == "" || passwordCheck == "")
    }

    fun passwordsDontMatch(): Boolean {
        return  password != passwordCheck
    }
}