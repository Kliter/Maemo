package com.kusumi.katsumi.maemo.Tools

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.kusumi.katsumi.maemo.Dictionary.DictionaryDBOpenHelper

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
                    "_id desc"
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

		fun deleteWord(db: SQLiteDatabase, deleteWordIdList: MutableList<Int>) {
			val deleteTerms = StringBuilder("_id in (")
			for (i in 0 until deleteWordIdList.size) {
				deleteTerms.append(deleteWordIdList[i])
				if (i != deleteWordIdList.size -1) {
					deleteTerms.append(" , ")
				}
			}
			deleteTerms.append(")")
			DictionaryDBOpenHelper.delete(db, deleteTerms.toString())
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