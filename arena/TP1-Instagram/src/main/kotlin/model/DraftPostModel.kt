package model

import org.uqbar.commons.model.annotations.Observable

@Observable
class DraftPostModel(postModel : PostModel) {
    var portrait = postModel.portrait
    var landscape = postModel.landscape
    var description = postModel.description

}