package com.kusumi.katsumi.maemo.Util

class Constants {
	companion object {
		// Create
		const val WORD_CREATE_ENTRY = "CREATE TABLE Word (_id INTEGER PRIMARY KEY, wordtitle TEXT, wordcontent TEXT, updatetime INTEGER)"
		const val MEMO_CREATE_ENTRY = "CREATE TABLE Memo (_id INTEGER PRIMARY KEY, fact TEXT, abstract TEXT, diversion TEXT, updatetime INTEGER)"

		//Drop
		const val WORD_DROP_ENTRY = "DROP TABLE IF EXISTS Word"
		const val MEMO_DROP_ENTRY = "DROP TABLE IF EXISTS Memo"

		// Page Info
		const val MEMO = "MEMO"
		const val MEMO_SECTION_NUM = 0
		const val WORD= "WORD"
		const val WORD_SECTION_NUM = 1
	}
}