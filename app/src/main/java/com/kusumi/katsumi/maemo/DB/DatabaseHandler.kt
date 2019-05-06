package com.kusumi.katsumi.maemo.DB

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.kusumi.katsumi.maemo.Util.Constants.Companion.MEMO_CREATE_ENTRY
import com.kusumi.katsumi.maemo.Util.Constants.Companion.WORD_CREATE_ENTRY
import com.kusumi.katsumi.maemo.Util.Constants.Companion.MEMO_DROP_ENTRY
import com.kusumi.katsumi.maemo.Util.Constants.Companion.WORD_DROP_ENTRY

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

		fun getWordData(db: SQLiteDatabase): Cursor {
			return db.query(
					"Word",
					arrayOf("_id", "wordtitle", "wordcontent"),
					null,
					null,
					null,
					null,
					"_id desc"
			)
		}

		fun updateWord(db: SQLiteDatabase, id: Int, wordTitle: String, wordContent: String): Boolean {
			return WordDBOpenHelper.update(db, id, wordTitle, wordContent)
		}

		fun insertWord(db: SQLiteDatabase, wordTitle: String, wordContent: String): Boolean {
			return WordDBOpenHelper.insert(db, wordTitle, wordContent)
		}

		fun delete(db: SQLiteDatabase, tableName: String, deleteWordIdList: MutableList<Int>) {
			val deleteTerms = StringBuilder("_id in (")
			for (i in 0 until deleteWordIdList.size) {
				deleteTerms.append(deleteWordIdList[i])
				if (i != deleteWordIdList.size -1) {
					deleteTerms.append(" , ")
				}
			}
			deleteTerms.append(")")
			BaseDBOpenHelper.delete(db, tableName, deleteTerms.toString())
		}

		fun existsTable(db: SQLiteDatabase, tableName: String): Cursor {
			return db.rawQuery(
				"SELECT count(*) FROM sqlite_master WHERE type='table' AND name='$tableName'",
				null
			)
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

		fun getMemoData(db: SQLiteDatabase, query: String): Cursor {
			return db.rawQuery(
				"SELECT * FROM Memo WHERE fact LIKE '%$query%' OR abstract LIKE '%$query%' OR diversion LIKE '%$query%'",
				null
			)
		}

		fun getWordData(db: SQLiteDatabase, query: String): Cursor {
			return db.rawQuery(
				"SELECT * FROM Word WHERE wordtitle LIKE '%$query%' OR wordcontent LIKE '%$query%'",
				null
			)
		}

		fun insertMemo(db: SQLiteDatabase, factText: String, abstractText: String, diversionText: String): Boolean {
			return MemoDBOpenHelper.insert(db, factText, abstractText, diversionText)
		}

		fun updateMemo(db: SQLiteDatabase, id: Int, factText: String, abstractText: String, diversionText: String): Boolean {
			return MemoDBOpenHelper.update(db, id, factText, abstractText, diversionText)
		}
	}
}