package com.kusumi.katsumi.maemo

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dictionary.*

class DictionaryActivity: AppCompatActivity(), DictionaryViewHolder.ItemClickListener {

	// DB Information
	private lateinit var dbOpenHelper: DictionaryDBOpenHelper
	private lateinit var dictionaryReadableDB: SQLiteDatabase
	private val TABLE_NAME = "Dictionary"
	private val TABLE_EXISTS = "1"

	// Word Information
	private lateinit var wordTitleList: MutableList<String>
	private lateinit var wordContentList: MutableList<String>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_dictionary)
		bottomNavigationView.selectedItemId = R.id.action_dictionary
		BottomNavigationViewManager.setupBottomNavigationView(this, bottomNavigationView)

		dbOpenHelper = DictionaryDBOpenHelper(this)
		dictionaryReadableDB = dbOpenHelper.readableDatabase

		val cursor: Cursor = DatabaseHandler.existsTable(dictionaryReadableDB, TABLE_NAME)
		cursor.moveToFirst()

		if (cursor.getString(0) == TABLE_EXISTS) {
			setupWordList()
		}

		rvWordCardContainer.adapter = DictionaryAdapter(this, this, wordTitleList, wordContentList)
		rvWordCardContainer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
	}

	private fun setupWordList() {
		wordTitleList = mutableListOf()
		wordContentList = mutableListOf()

		val cursor = DatabaseHandler.getWordData(dbOpenHelper.readableDatabase)
		cursor.moveToFirst()
		for (i in 0 until cursor.count) {
			wordTitleList.add(cursor.getString(0))
			wordContentList.add(cursor.getString(1))
		}
		cursor.close()
	}

	override fun onItemClick(view: View, position: Int) {
		Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
	}
}