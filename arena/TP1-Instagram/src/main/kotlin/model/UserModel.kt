package model

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel(user : User,system : InstagramSystem) {

    var id : String = user.id
    var name : String = user.name
    var email : String = user.email






}