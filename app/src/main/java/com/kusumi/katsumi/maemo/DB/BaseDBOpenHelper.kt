package com.kusumi.katsumi.maemo.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kusumi.katsumi.maemo.Util.DatabaseHandler

open class BaseDBOpenHelper(context: Context):
	SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

	companion object {
		const val INSERT_FAILED = -1L
		const val UPDATE_SUCCEED = 1

		private const val DATABASE_VERSION = 1
		private const val DATABASE_NAME = "MaemoDB.db"

		/**
		 * Delete word data according to deleteTerms.
		 *
		 * @param db
		 * @param deleteTerms This param is used for whereClause of db.delete()
		 */
		fun delete(db: SQLiteDatabase, tableName: String, deleteTerms: String) {
			db.delete(tableName, deleteTerms, null)
		}
	}

	override fun onCreate(db: SQLiteDatabase) {
		DatabaseHandler.initDB(db)
	}

	override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
		onUpgrade(db, oldVersion, newVersion)
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
		DatabaseHandler.dropAllTable(db)
		onCreate(db)
	}
}