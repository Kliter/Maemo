package com.kusumi.katsumi.maemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DatabaseHandler(context: Context) {

	companion object {

		/**
		 * Get Dictionary data from "Dictionary" table.
		 */
		fun getWordData(db: SQLiteDatabase): Cursor {
            val cursor: Cursor = db.query(
                    "Dictionary",
                    arrayOf("_id", "wordtitle", "wordcontent"),
                    null,
                    null,
                    null,
                    null,
                    null
            )
			return cursor
		}

		/**
		 * Call DictionaryDBOpenHandler#update().
		 * If update process is succeed, this method will return true.
		 */
		fun updateWord(db: SQLiteDatabase, id: Int, wordTitle: String, wordContent: String): Boolean {
			return DictionaryDBOpenHelper.update(db, id, wordTitle, wordContent)
		}

		/**
		 * Call DictionaryDBOpenHandler#insert().
		 * If insert process is succeed, this method will return true.
		 */
		fun insertWord(db: SQLiteDatabase, wordTitle: String, wordContent: String): Boolean {
			return DictionaryDBOpenHelper.insert(db, wordTitle, wordContent)
		}

		/**
		 * Confirm if the table that matched passed tableName is exists.
		 */
		fun existsTable(db: SQLiteDatabase, tableName: String): Cursor {
			val query = "select count(*) from sqlite_master where type='table' and name='$tableName'"
			return db.rawQuery(query, null)
		}
	}
}