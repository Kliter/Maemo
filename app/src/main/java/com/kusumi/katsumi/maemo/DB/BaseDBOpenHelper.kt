package com.kusumi.katsumi.maemo.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

abstract class BaseDBOpenHelper(context: Context):
	SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

	companion object {
		const val INSERT_FAILED = -1L
		const val UPDATE_SUCCEED = 1

		private const val DATABASE_VERSION = 1
		private const val DATABASE_NAME = "Maemo.db"
	}

	override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
		onUpgrade(db, oldVersion, newVersion)
	}
}