package com.kusumi.katsumi.maemo

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
				arrayOf("wordTitle", "wordContent"),
				null,
				null,
				null,
				null,
				null
			)
			return cursor
		}

		/**
		 * Confirm if table that matched passed tableName is exists.
		 */
		fun existsTable(db: SQLiteDatabase, tableName: String): Cursor {
			val query = "select count(*) from sqlite_master where type='table' and name='$tableName'"
			val cursor: Cursor = db.rawQuery(query, null)
			return cursor
		}
	}
}