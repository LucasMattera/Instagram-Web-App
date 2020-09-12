package model

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel(user : User,val system : InstagramSystem) {
    var id : String = user.id
    var name : String = user.name
    var email : String = user.email
    var posts = system.searchByUserId(id).map { PostModel(it.id, it.description, it.landscape, it.portrait) }
    var selected : PostModel? = null
    var description : String = ""

    fun filterByTag(description : String) {
        this.posts = posts.filter { it.description.contains(description) }
    }


}