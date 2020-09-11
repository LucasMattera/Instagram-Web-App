package model

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel(user : User,val system : InstagramSystem) {
    var id : String = user.id
    var name : String = user.name
    var email : String = user.email
    var posts = system.searchByUserId(id).map { PostModel(it.id, it.description) }
    var selected : PostModel? = null
}