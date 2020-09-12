package model

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel(user : User,val system : InstagramSystem) {
    var id : String = user.id
    var name : String = user.name
    var email : String = user.email
    var posts = allPost()
    var postDuplicate = allPost()
    var selected : PostModel? = null
    var description : String = ""

    fun filterByDescription(description : String) {
        this.posts = posts.filter { it.description.contains(description) }
    }

    fun resetPost() {
        this.posts = postDuplicate
    }

    fun allPost() : List<PostModel> {
        var posts = system.searchByUserId(id).map { PostModel(it.id, it.description, it.landscape, it.portrait) }
        return posts
    }

}