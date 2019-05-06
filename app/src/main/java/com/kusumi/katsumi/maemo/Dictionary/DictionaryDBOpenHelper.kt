package com.kusumi.katsumi.maemo.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class WordDBOpenHelper(context: Context): BaseDBOpenHelper(context) {

	companion object {
		private const val TABLE_NAME = "Word"
		private const val _ID = "_id"

		fun update(db: SQLiteDatabase, id: Int, wordTitle: String, wordContent: String): Boolean {
			val values = ContentValues()
			values.put("wordtitle", wordTitle)
			values.put("wordcontent", wordContent)
			return db.update(TABLE_NAME, values, "$_ID = $id", null) == UPDATE_SUCCEED
		}

		fun insert(db: SQLiteDatabase, wordTitle: String, wordContent: String): Boolean {
			val values = ContentValues()
			values.put("wordtitle", wordTitle)
			values.put("wordcontent", wordContent)
			return db.insert(TABLE_NAME, null, values) != INSERT_FAILED
		}
	}
}