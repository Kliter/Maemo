package com.kusumi.katsumi.maemo.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class WordDBOpenHelper(context: Context): BaseDBOpenHelper(context) {

	companion object {
		private const val TABLE_NAME = "Dictionary"
		private const val _ID = "_id"
		private const val COLUMN_NAME_WORDTITLE = "wordtitle"
		private const val COLUMN_NAME_WORDCONTENT = "wordcontent"

		/**
		 * Update word data(wordtitle, wordcontent) from word dialog.
		 *
		 * @param db It will be passed writable database.
		 * @param wordTitle Entered wordTitle.
		 * @param wordContent Entered WordContent.
		 * @return If update process is succeed, this method will return true.
		 */
		fun update(db: SQLiteDatabase, id: Int, wordTitle: String, wordContent: String): Boolean {
			val values = ContentValues()
			values.put("wordtitle", wordTitle)
			values.put("wordcontent", wordContent)
			return db.update(TABLE_NAME, values, "$_ID = $id", null) == UPDATE_SUCCEED
		}

		/**
		 * Insert word data(wordtitle, wordcontent) from word dialog.
		 *
		 * @param db It will be passed writable database.
		 * @param wordTitle Entered wordTitle.
		 * @param wordContent Entered WordContent.
		 * @return If insert process is succeed, this method will return true.
		 */
		fun insert(db: SQLiteDatabase, wordTitle: String, wordContent: String): Boolean {
			val values = ContentValues()
			values.put("wordtitle", wordTitle)
			values.put("wordcontent", wordContent)
			return db.insert(TABLE_NAME, null, values) != INSERT_FAILED
		}
	}
}