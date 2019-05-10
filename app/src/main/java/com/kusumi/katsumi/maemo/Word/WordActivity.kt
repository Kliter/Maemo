package com.kusumi.katsumi.maemo.Word

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.kusumi.katsumi.maemo.*
import com.kusumi.katsumi.maemo.DB.WordDBOpenHelper
import com.kusumi.katsumi.maemo.Model.Word
import com.kusumi.katsumi.maemo.DB.DatabaseHandler
import com.kusumi.katsumi.maemo.Interface.ItemClickListener
import com.kusumi.katsumi.maemo.Interface.PositiveButtonClickListener
import com.kusumi.katsumi.maemo.Util.BottomNavigationViewManager
import com.kusumi.katsumi.maemo.Util.ToolbarManager
import kotlinx.android.synthetic.main.activity_word.*
import kotlinx.android.synthetic.main.snippet_toolbar.*

class WordActivity: AppCompatActivity(), ItemClickListener, PositiveButtonClickListener {

    // Bundle key
	private val WORD = "WORD"

	// DB Information
	private lateinit var dbOpenHelper: WordDBOpenHelper
	private lateinit var wordReadableDB: SQLiteDatabase
	private val TABLE_NAME = "Word"
	private val TABLE_EXISTS = "1"

	// Word Information
	private lateinit var mWordList: MutableList<Word>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_word)

		setupWidgets()
	}

	private fun setupWidgets() {
		setupBottomNavigationView()
		ToolbarManager.setupToolbar(this, appbar)
		mWordList = mutableListOf()

		floating_action_button.setOnClickListener {
			WordDialogFragment().show(supportFragmentManager, "launch")
		}

		dbOpenHelper = WordDBOpenHelper(this)
		wordReadableDB = dbOpenHelper.readableDatabase

		val cursor: Cursor = DatabaseHandler.existsTable(wordReadableDB, TABLE_NAME)
		cursor.moveToFirst()
		if (cursor.getString(0) == TABLE_EXISTS) {
			setupWordList()
		}

		recycler_view.adapter = WordListAdapter(this, this, mWordList)
		recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
	}

	private fun setupBottomNavigationView() {
		bottom_navigation_view.selectedItemId = R.id.action_word
		BottomNavigationViewManager.setupBottomNavigationView(this, bottom_navigation_view)
	}

	private fun setupWordList() {
		val cursor = DatabaseHandler.getWordData(dbOpenHelper.readableDatabase)
		cursor.moveToFirst()
		for (i in 0 until cursor.count) {
			val word = Word()
			word._id = cursor.getInt(0)
			word.wordTitle = cursor.getString(1)
			word.wordContent = cursor.getString(2)
			word.updateTime = cursor.getLong(3)
			mWordList.add(word)
			cursor.moveToNext()
		}
		cursor.close()
	}

	override fun onItemClick(view: View, position: Int) {
        val fragment = WordDialogFragment()
        val arguments = Bundle()
		arguments.putSerializable(
				WORD,
			Word(
				mWordList[position]._id,
				mWordList[position].wordTitle,
				mWordList[position].wordContent
			)
		)
        fragment.arguments = arguments
        fragment.show(supportFragmentManager, "launch")
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_top, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if (item?.itemId == R.id.action_deletete) {
			AlertDialog.Builder(this)
				.setTitle(getString(R.string.ConfirmDeleteWord))
				.setMessage(getString(R.string.DialogConfirmText))
				.setPositiveButton(getString(R.string.OK)) { _, _ ->
					if (item.itemId == R.id.action_deletete) {
						val deleteWordIdList = mutableListOf<Int>()
						for (i in 0 until mWordList.size) {
							val word = (recycler_view.adapter as WordListAdapter).wordList[i]
							if (word.isSelected) {
								deleteWordIdList.add(word._id)
							}
						}
						DatabaseHandler.delete(
							WordDBOpenHelper(this).writableDatabase, "Word", deleteWordIdList
						)
						reload()
					}

				}
				.setNegativeButton(getString(R.string.Close), null)
				.show()
		}
		return super.onOptionsItemSelected(item)
	}

	private fun reload() {
		val intent = intent
		overridePendingTransition(0, 0)
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
		finish()

		overridePendingTransition(0, 0)
		startActivity(intent)
	}

	override fun onPositiveButtonClick() {
		reload()
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}
}