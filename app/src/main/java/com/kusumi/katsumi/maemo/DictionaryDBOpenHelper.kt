package com.kusumi.katsumi.maemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DictionaryDBOpenHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

	companion object {
		private const val DATABASE_VERSION = 1
		private const val DATABASE_NAME = "MaemoDB.db"
		private const val TABLE_NAME = "Dictionary"
		private const val _ID = "_id"
		private const val COLUMN_NAME_WORDTITLE = "wordtitle"
		private const val COLUMN_NAME_WORDCONTENT = "wordcontent"

		private const val SQL_CREATE_ENTRIES =
			"CREATE TABLE $TABLE_NAME ($_ID INTEGER PRIMARY KEY, $COLUMN_NAME_WORDTITLE TEXT, $COLUMN_NAME_WORDCONTENT TEXT)"

		private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
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

	public fun insert(db: SQLiteDatabase, wordTitle: String, wordContent: String) {
		val values: ContentValues = ContentValues()
		values.put("wordTitle", wordTitle)
		values.put("wordContent", wordContent)
		db.insert(TABLE_NAME, null, values)

	}
}