package com.kusumi.katsumi.maemo.Memo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kusumi.katsumi.maemo.DB.BaseDBOpenHelper

class MemoDBOpenHelper(context: Context): BaseDBOpenHelper(context) {

	companion object {

	}

	override fun onCreate(db: SQLiteDatabase?) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

}