package com.kusumi.katsumi.maemo.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kusumi.katsumi.maemo.Util.DateConverter

class MemoDBOpenHelper(context: Context): BaseDBOpenHelper(context) {

	companion object {
		private const val TABLE_NAME = "Memo"
		private const val _ID = "_id"

		fun update(db: SQLiteDatabase, id: Int, fact: String, abstract: String, diversion: String):Boolean {
			val values = ContentValues()
			values.put("fact", fact)
			values.put("abstract", abstract)
			values.put("diversion", diversion)
			values.put("updatetime", DateConverter.getCurrentDate())
			return db.update(TABLE_NAME, values, "$_ID = $id", null) == UPDATE_SUCCEED
		}

		fun insert(db: SQLiteDatabase, fact: String, abstract: String, diversion: String): Boolean {
			val values = ContentValues()
			values.put("fact", fact)
			values.put("abstract", abstract)
			values.put("diversion", diversion)
			values.put("updatetime", DateConverter.getCurrentDate())
			return db.insert(TABLE_NAME, null, values) != INSERT_FAILED
		}
	}
}