package com.kusumi.katsumi.maemo.Model

import java.io.Serializable

class Memo(): Serializable {

	var isSelected: Boolean = false
	var _id: Int = 0
	var factText: String? = null
	var abstractText: String? = null
	var diversionText: String? = null
	var updateTime: Long? = null

	constructor(id: Int, factText: String?, abstractText: String?, diversionText: String?): this() {
		this._id = id
		this.factText = factText
		this.abstractText = abstractText
		this.diversionText = diversionText
	}
}