package com.kusumi.katsumi.maemo.Search

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kusumi.katsumi.maemo.DB.BaseDBOpenHelper
import com.kusumi.katsumi.maemo.R
import com.kusumi.katsumi.maemo.Util.BottomNavigationViewManager
import com.kusumi.katsumi.maemo.DB.DatabaseHandler
import com.kusumi.katsumi.maemo.DB.MemoDBOpenHelper
import com.kusumi.katsumi.maemo.DB.WordDBOpenHelper
import com.kusumi.katsumi.maemo.Interface.ItemClickListener
import com.kusumi.katsumi.maemo.Interface.PositiveButtonClickListener
import com.kusumi.katsumi.maemo.Memo.MemoDialogFragment
import com.kusumi.katsumi.maemo.Memo.MemoListAdapter
import com.kusumi.katsumi.maemo.Model.Memo
import com.kusumi.katsumi.maemo.Model.Word
import com.kusumi.katsumi.maemo.Util.Constants
import com.kusumi.katsumi.maemo.Util.Constants.Companion.MEMO
import com.kusumi.katsumi.maemo.Util.Constants.Companion.MEMO_SECTION_NUM
import com.kusumi.katsumi.maemo.Util.Constants.Companion.WORD
import com.kusumi.katsumi.maemo.Util.Constants.Companion.WORD_SECTION_NUM
import com.kusumi.katsumi.maemo.Util.ToolbarManager
import com.kusumi.katsumi.maemo.Word.WordDialogFragment
import com.kusumi.katsumi.maemo.Word.WordListAdapter
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_search_content.*
import kotlinx.android.synthetic.main.snippet_toolbar.*

class SearchActivity: AppCompatActivity(), ItemClickListener, PositiveButtonClickListener {

	private lateinit var mDBOpenHelper: BaseDBOpenHelper

	private var mMemoList: MutableList<Memo>? = null
	private var mWordList: MutableList<Word>? = null

	private var mCurrentSelectedTabNum: Int = 0
	private var mRecentUsedQuery: String? = null
	private var mSearchPagerAdapter: SearchPagerAdapter? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_search)
		setupWidgets()
	}

	private fun setupWidgets() {
		ToolbarManager.setupToolbar(this, appbar)
		initListData()
		setupBottomNavigationView()
		setupViewPager()
		setupSearchView()
	}

	private fun setupViewPager() {
		mSearchPagerAdapter = SearchPagerAdapter(supportFragmentManager)
		viewpager.adapter = mSearchPagerAdapter

		// In order to prepare for when the tab is scrolled or selected, Setup the viewpager.
		viewpager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
			override fun onPageScrollStateChanged(position: Int) {}

			override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

			// Either scrolling or selecting, onPageSelected() will be launched.
			override fun onPageSelected(position: Int) {
				mCurrentSelectedTabNum = position
			}
		})
		tab_layout.setupWithViewPager(viewpager)
	}

	private fun setupSearchView() {
		val searchView = MaterialSearchView(this)
		searchView.layoutParams = ViewGroup.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT
		)

		val viewGroup: ViewGroup = frame_layout
		viewGroup.addView(searchView)
	}

	private fun setupRecyclerView() {
		if (mCurrentSelectedTabNum == MEMO_SECTION_NUM) {
			mSearchPagerAdapter?.mMemoFragment?.recycler_view?.adapter =
				MemoListAdapter(this, this, mMemoList!!)
			mSearchPagerAdapter?.mMemoFragment?.recycler_view?.layoutManager =
				LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		}
		else if (mCurrentSelectedTabNum == WORD_SECTION_NUM) {
			mSearchPagerAdapter?.mWordFragment?.recycler_view?.adapter =
				WordListAdapter(this, this, mWordList!!)
			mSearchPagerAdapter?.mWordFragment?.recycler_view?.layoutManager =
				LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		}
	}

	private fun setupBottomNavigationView() {
		bottom_navigation_view.selectedItemId = R.id.action_search
		BottomNavigationViewManager.setupBottomNavigationView(this, bottom_navigation_view)
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_search, menu)
		if (menu != null) {
			val item: MenuItem = menu.findItem(R.id.action_search)
			material_search_view.setMenuItem(item)
			material_search_view.setOnQueryTextListener(object: MaterialSearchView.OnQueryTextListener{
				override fun onQueryTextSubmit(query: String?): Boolean {
					mRecentUsedQuery = query
					// Get searched data.
					if (query != null) {
						initListData()
						getQueriedData(query)

						// Setup RecyclerView
						setupRecyclerView()
					}
					return true
				}

				override fun onQueryTextChange(newText: String?): Boolean {
					return false
				}
			})
		}
		return true
	}

	private fun getQueriedData(query: String) {
		when (mCurrentSelectedTabNum) {
			// Memo
			0 -> {
				mDBOpenHelper = MemoDBOpenHelper(this@SearchActivity)
				val cursor: Cursor = DatabaseHandler.getMemoData(mDBOpenHelper.readableDatabase, query)
				cursor.moveToFirst()
				for (i in 0 until cursor.count) {
					val memo = Memo()
					memo._id = cursor.getInt(0)
					memo.factText = cursor.getString(1)
					memo.abstractText = cursor.getString(2)
					memo.diversionText = cursor.getString(3)
					mMemoList?.add(memo)
					cursor.moveToNext()
				}
				cursor.close()
			}

			// Word
			1 -> {
				mDBOpenHelper = WordDBOpenHelper(this@SearchActivity)
				val cursor: Cursor = DatabaseHandler.getWordData(mDBOpenHelper.readableDatabase, query)
				cursor.moveToFirst()
				for (i in 0 until cursor.count) {
					val word = Word()
					word._id = cursor.getInt(0)
					word.wordTitle = cursor.getString(1)
					word.wordContent = cursor.getString(2)
					mWordList?.add(word)
					cursor.moveToNext()
				}
				cursor.close()
			}
		}
	}

	private fun initListData() {
		mMemoList = null
		mMemoList = mutableListOf()
		mWordList = null
		mWordList = mutableListOf()
	}

	override fun onItemClick(view: View, position: Int) {
		Toast.makeText(this, "Clicked $position", Toast.LENGTH_SHORT).show()

		val arguments = Bundle()
		if (mCurrentSelectedTabNum == MEMO_SECTION_NUM) {
			val fragment = MemoDialogFragment()
			arguments.putSerializable(
				MEMO,
				Memo(
					mMemoList!![position]._id,
					mMemoList!![position].factText,
					mMemoList!![position].abstractText,
					mMemoList!![position].diversionText
				)
			)
			fragment.arguments = arguments
			fragment.show(supportFragmentManager, "launch")
		}
		else if (mCurrentSelectedTabNum == WORD_SECTION_NUM) {
			val fragment = WordDialogFragment()
			arguments.putSerializable(
				WORD,
				Word(
					mWordList!![position]._id,
					mWordList!![position].wordTitle,
					mWordList!![position].wordContent
				)
			)
			fragment.arguments = arguments
			fragment.show(supportFragmentManager, "launch")
		}
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if (item?.itemId == R.id.action_deletete) {
			val deleteIdList = mutableListOf<Int>()
			if (mCurrentSelectedTabNum == MEMO_SECTION_NUM) {
				val recyclerView = mSearchPagerAdapter?.mMemoFragment?.recycler_view
				for (i in 0 until mMemoList?.size!!) {
					val memo = (recyclerView?.adapter as MemoListAdapter).memoList[i]
					if (memo.isSelected) {
						deleteIdList.add(memo._id)
					}
				}
				DatabaseHandler.delete(
					MemoDBOpenHelper(this).writableDatabase, "Memo", deleteIdList
				)
			}
			else if (mCurrentSelectedTabNum == WORD_SECTION_NUM) {
				val recyclerView = mSearchPagerAdapter?.mWordFragment?.recycler_view
				for (i in 0 until mWordList?.size!!) {
					val word = (recyclerView?.adapter as WordListAdapter).wordList[i]
					if (word.isSelected) {
						deleteIdList.add(word._id)
					}
				}
				DatabaseHandler.delete(
					WordDBOpenHelper(this).writableDatabase, "Word", deleteIdList
				)
			}
			initListData()
			if (mRecentUsedQuery != null) {
				getQueriedData(mRecentUsedQuery!!)
			}
			setupRecyclerView()
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onRestart() {
		super.onRestart()
		setupWidgets()
	}

	override fun onPositiveButtonClick() {
		initListData()
		getQueriedData(mRecentUsedQuery!!)
		setupRecyclerView()
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}
}