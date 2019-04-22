package com.kusumi.katsumi.maemo

import java.io.Serializable

class Word(): Serializable {

    var _id: Int = 0
    var wordTitle: String? = null
    var wordContent: String? = null

    constructor(id: Int, wordTitle: String?, wordContent: String?): this() {
        this._id = id
        this.wordTitle = wordTitle
        this.wordContent = wordContent
    }
}