package com.kusumi.katsumi.maemo.Util

class Constants {
	companion object {
		// Create
		const val WORD_CREATE_ENTRY = "CREATE TABLE Dictionary (_id INTEGER PRIMARY KEY, wordtitle TEXT, wordcontent TEXT)"
		const val MEMO_CREATE_ENTRY = "CREATE TABLE Memo (_id INTEGER PRIMARY KEY, fact TEXT, abstract TEXT, diversion TEXT)"

		//Drop
		const val WORD_DROP_ENTRY = "DROP TABLE IF EXISTS Dictionary"
		const val MEMO_DROP_ENTRY = "DROP TABLE IF EXISTS Memo"
	}
}