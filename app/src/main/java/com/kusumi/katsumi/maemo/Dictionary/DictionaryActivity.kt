package com.kusumi.katsumi.maemo.Dictionary

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.kusumi.katsumi.maemo.*
import com.kusumi.katsumi.maemo.Model.Word
import com.kusumi.katsumi.maemo.Tools.DatabaseHandler
import kotlinx.android.synthetic.main.activity_dictionary.*
import kotlinx.android.synthetic.main.snippet_toolbar.*

class DictionaryActivity: AppCompatActivity(),
	DictionaryViewHolder.ItemClickListener {

    // Bundle key
	private val WORD = "WORD"

	// DB Information
	private lateinit var dbOpenHelper: DictionaryDBOpenHelper
	private lateinit var dictionaryReadableDB: SQLiteDatabase
	private val TABLE_NAME = "Dictionary"
	private val TABLE_EXISTS = "1"

	// Word Information
	private lateinit var wordList: MutableList<Word>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_dictionary)

		setupWidgets()
	}

	private fun setupWidgets() {
		val toolbar: Toolbar = appbar
		this.setSupportActionBar(toolbar)
		supportActionBar?.let {
			it.setDisplayHomeAsUpEnabled(true)
			it.setHomeButtonEnabled(true)
		}

		bottomNavigationView.selectedItemId = R.id.action_dictionary
		BottomNavigationViewManager.setupBottomNavigationView(
			this,
			bottomNavigationView
		)

		fabInsertWord.setOnClickListener {
			WordDialogFragment().show(supportFragmentManager, "launch")
		}

		dbOpenHelper = DictionaryDBOpenHelper(this)
		dictionaryReadableDB = dbOpenHelper.readableDatabase

		val cursor: Cursor =
			DatabaseHandler.existsTable(dictionaryReadableDB, TABLE_NAME)
		cursor.moveToFirst()

		if (cursor.getString(0) == TABLE_EXISTS) {
			setupWordList()
		}

		rvWordCardContainer.adapter = DictionaryAdapter(this, this, wordList)
		rvWordCardContainer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
	}

	private fun setupWordList() {
		wordList = mutableListOf()

		val cursor = DatabaseHandler.getWordData(dbOpenHelper.readableDatabase)
		cursor.moveToFirst()
		for (i in 0 until cursor.count) {
			val word = Word()
			word._id = cursor.getInt(0)
			word.wordTitle = cursor.getString(1)
			word.wordContent = cursor.getString(2)
			wordList.add(word)
			cursor.moveToNext()
		}
		cursor.close()
	}

	override fun onItemClick(view: View, position: Int) {
		Toast.makeText(this, "Clicked $position", Toast.LENGTH_SHORT).show()
        val fragment = WordDialogFragment()
        val arguments = Bundle()
		arguments.putSerializable(
				WORD,
			Word(
				wordList[position]._id,
				wordList[position].wordTitle,
				wordList[position].wordContent
			)
		)
        fragment.arguments = arguments
        fragment.show(supportFragmentManager, "launch")
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_dictionary, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if (item?.itemId == R.id.actionDelete) {
			val deleteWordIdList = mutableListOf<Int>()
			for (i in 0 until wordList.size) {
				val word = (rvWordCardContainer.adapter as DictionaryAdapter).wordList.get(i)
				if (word.isSelected) {
					deleteWordIdList.add(word._id)
				}
			}
			DatabaseHandler.deleteWord(
				DictionaryDBOpenHelper(
					this
				).writableDatabase, deleteWordIdList
			)
			reload()
		}
		return super.onOptionsItemSelected(item)
	}

	fun reload() {
		val intent = intent
		overridePendingTransition(0, 0)
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
		finish()

		overridePendingTransition(0, 0)
		startActivity(intent)
	}

	override fun onRestart() {
		super.onRestart()
		setupWidgets()
	}
}