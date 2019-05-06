package com.kusumi.katsumi.maemo.Util

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.kusumi.katsumi.maemo.DB.BaseDBOpenHelper
import com.kusumi.katsumi.maemo.Util.Constants.Companion.MEMO_CREATE_ENTRY
import com.kusumi.katsumi.maemo.Util.Constants.Companion.WORD_CREATE_ENTRY
import com.kusumi.katsumi.maemo.DB.MemoDBOpenHelper
import com.kusumi.katsumi.maemo.Util.Constants.Companion.MEMO_DROP_ENTRY
import com.kusumi.katsumi.maemo.Util.Constants.Companion.WORD_DROP_ENTRY
import com.kusumi.katsumi.maemo.DB.WordDBOpenHelper

class DatabaseHandler {

	companion object {

		fun initDB(db: SQLiteDatabase) {
			db.execSQL(WORD_CREATE_ENTRY)
			db.execSQL(MEMO_CREATE_ENTRY)
		}

		fun dropAllTable(db: SQLiteDatabase) {
			db.execSQL(WORD_DROP_ENTRY)
			db.execSQL(MEMO_DROP_ENTRY)
		}

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
			return WordDBOpenHelper.update(db, id, wordTitle, wordContent)
		}

		/**
		 * Call DictionaryDBOpenHandler#insert().
		 * If insert process is succeed, this method will return true.
		 */
		fun insertWord(db: SQLiteDatabase, wordTitle: String, wordContent: String): Boolean {
			return WordDBOpenHelper.insert(db, wordTitle, wordContent)
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
			BaseDBOpenHelper.delete(db, "Dictionary", deleteTerms.toString())
		}

		/**
		 * Confirm if the table that matched passed tableName is exists.
		 */
		fun existsTable(db: SQLiteDatabase, tableName: String): Cursor {
			val query = "select count(*) from sqlite_master where type='table' and name='$tableName'"
			return db.rawQuery(query, null)
		}

		fun getMemoData(db: SQLiteDatabase): Cursor {
			return db.query(
				"Memo",
				arrayOf("_id", "fact", "abstract", "diversion"),
				null,
				null,
				null,
				null,
				"_id desc"
			)
		}

//		fun getRecentMemoData(db: SQLiteDatabase): Cursor {
//			val query = "select * from sqlite_master where  "
//			return db.query(
//				"Memo",
//				arrayOf("_id", "fact", "abstract", "diversion"),
//				null,
//				null,
//				null,
//				null,
//				"_id desc"
//			)
//		}

		fun insertMemo(db: SQLiteDatabase, factText: String, abstractText: String, diversionText: String): Boolean {
			return MemoDBOpenHelper.insert(db, factText, abstractText, diversionText)
		}

		fun updateMemo(db: SQLiteDatabase, id: Int, factText: String, abstractText: String, diversionText: String): Boolean {
			return MemoDBOpenHelper.update(db, id, factText, abstractText, diversionText)
		}
	}
}