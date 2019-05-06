package com.kusumi.katsumi.maemo.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MemoDBOpenHelper(context: Context): BaseDBOpenHelper(context) {

	companion object {
		private const val TABLE_NAME = "Memo"
		private const val _ID = "_id"
		private const val COLUMN_NAME_MEMOFACT = "fact"
		private const val COLUMN_NAME_MEMOABSTRACT = "abstract"
		private const val COLUMN_NAME_MEMODIVERSION = "diversion"

		fun update(db: SQLiteDatabase, id: Int, fact: String, abstract: String, diversion: String):Boolean {
			val values = ContentValues()
			values.put("fact", fact)
			values.put("abstract", abstract)
			values.put("diversion", diversion)
			return db.update(TABLE_NAME, values, "$_ID = $id", null) == UPDATE_SUCCEED
		}

		fun insert(db: SQLiteDatabase, fact: String, abstract: String, diversion: String): Boolean {
			val values = ContentValues()
			values.put("fact", fact)
			values.put("abstract", abstract)
			values.put("diversion", diversion)
			return db.insert(TABLE_NAME, null, values) != INSERT_FAILED
		}
	}
}