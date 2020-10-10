package model

import org.uqbar.commons.model.annotations.Observable

@Observable
class DraftPostModel() {
    var portrait = ""
    var landscape = ""
    var description = ""

    constructor (postModel: PostModel): this() {
        portrait = postModel.portrait
        landscape = postModel.landscape
        description = postModel.description

    }

    fun theFieldPortraitIsEmpty() : Boolean {
        return (portrait == "" )
    }

    fun theFieldLandscapeIsEmpty() : Boolean {
        return (landscape == "")
    }

    fun theFieldDescriptionIsEmpty() : Boolean {
        return (description == "")
    }

}