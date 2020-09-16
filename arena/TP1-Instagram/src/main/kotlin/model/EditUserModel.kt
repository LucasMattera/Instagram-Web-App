package model

import org.uqbar.commons.model.annotations.Observable

@Observable
class EditUserModel(var name : String,var password : String, var image : String) {
    fun algunosDeLosCamposDelUsuarioEstanVacios(): Boolean {
        return  (name == "" ||password == "" || image == "")
    }
}