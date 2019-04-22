package com.kusumi.katsumi.maemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DictionaryDBOpenHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

	companion object {
		private const val INSERT_FAILED = -1L
		private const val UPDATE_SUCCEED = 1

		private const val DATABASE_VERSION = 1
		private const val DATABASE_NAME = "MaemoDB.db"
		private const val TABLE_NAME = "Dictionary"
		private const val _ID = "_id"
		private const val COLUMN_NAME_WORDTITLE = "wordtitle"
		private const val COLUMN_NAME_WORDCONTENT = "wordcontent"

		private const val SQL_CREATE_ENTRIES =
			"CREATE TABLE $TABLE_NAME ($_ID INTEGER PRIMARY KEY, $COLUMN_NAME_WORDTITLE TEXT, $COLUMN_NAME_WORDCONTENT TEXT)"

		private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

		fun update(db: SQLiteDatabase, id: Int, wordTitle: String, wordContent: String): Boolean {
			val values = ContentValues()
			values.put("wordtitle", wordTitle)
			values.put("wordcontent", wordContent)
			return db.update(TABLE_NAME, values, "$_ID = $id", null) == UPDATE_SUCCEED
		}

		/**
		 * Insert word data(wordtitle and wordcontent) from word dialog.
		 * If insert process is succeed, this method will return true.
		 */
		fun insert(db: SQLiteDatabase, wordTitle: String, wordContent: String): Boolean {
			val values = ContentValues()
			values.put("wordtitle", wordTitle)
			values.put("wordcontent", wordContent)
			return db.insert(TABLE_NAME, null, values) != INSERT_FAILED
		}
	}

	override fun onCreate(db: SQLiteDatabase) {
		db.execSQL(SQL_CREATE_ENTRIES)
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
		db.execSQL(SQL_DELETE_ENTRIES)
	}

	override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
		onUpgrade(db, oldVersion, newVersion)
	}
}