package com.kusumi.katsumi.maemo.Memo

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.kusumi.katsumi.maemo.DB.MemoDBOpenHelper
import com.kusumi.katsumi.maemo.Util.BottomNavigationViewManager
import com.kusumi.katsumi.maemo.Model.Memo
import com.kusumi.katsumi.maemo.R
import com.kusumi.katsumi.maemo.Util.ToolbarManager
import com.kusumi.katsumi.maemo.DB.DatabaseHandler
import com.kusumi.katsumi.maemo.Interface.ItemClickListener
import com.kusumi.katsumi.maemo.Interface.PositiveButtonClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.snippet_toolbar.*

class MainActivity : AppCompatActivity(), ItemClickListener, PositiveButtonClickListener {

    // Bundle key
    private val MEMO = "MEMO"

    private lateinit var dbOpenHelper: MemoDBOpenHelper
    private lateinit var memoReadableDB: SQLiteDatabase
    private val TABLE_NAME = "Memo"
    private val TABLE_EXISTS = "1"

    private lateinit var mMemoList: MutableList<Memo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupWidget()
    }

    private fun setupWidget() {
        ToolbarManager.setupToolbar(this, appbar)
        setupBottomNavigationView()
        mMemoList = mutableListOf()

        floating_action_button.setOnClickListener {
            MemoDialogFragment().show(supportFragmentManager, "launch")
        }

        dbOpenHelper = MemoDBOpenHelper(this)
        memoReadableDB = dbOpenHelper.readableDatabase

        val cursor: Cursor = DatabaseHandler.existsTable(memoReadableDB, TABLE_NAME)
        cursor.moveToFirst()
        if (cursor.getString(0) == TABLE_EXISTS) {
            setupMemoList()
        }

        recycler_view.adapter = MemoListAdapter(this, this, mMemoList)
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupBottomNavigationView() {
        bottom_navigation_view.selectedItemId = R.id.action_home
        BottomNavigationViewManager.setupBottomNavigationView(this, bottom_navigation_view)
    }

    private fun setupMemoList() {
        val cursor = DatabaseHandler.getMemoData(dbOpenHelper.readableDatabase)
        cursor.moveToFirst()
        for (i in 0 until cursor.count) {
            val memo = Memo()
            memo._id = cursor.getInt(0)
            memo.factText = cursor.getString(1)
            memo.abstractText = cursor.getString(2)
            memo.diversionText = cursor.getString(3)
            memo.updateTime = cursor.getLong(4)
            mMemoList.add(memo)
            cursor.moveToNext()
        }
        cursor.close()
    }

    override fun onItemClick(view: View, position: Int) {
        val fragment = MemoDialogFragment()
        val arguments = Bundle()
        arguments.putSerializable(
            MEMO,
            Memo(
                mMemoList[position]._id,
                mMemoList[position].factText,
                mMemoList[position].abstractText,
                mMemoList[position].diversionText
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
            val deleteMemoIdList = mutableListOf<Int>()
            for (i in 0 until mMemoList.size) {
                val memo = (recycler_view.adapter as MemoListAdapter).memoList[i]
                if (memo.isSelected) {
                    deleteMemoIdList.add(memo._id)
                }
            }
            DatabaseHandler.delete(
                MemoDBOpenHelper(this).writableDatabase, "Memo", deleteMemoIdList
            )
            reload()
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

    override fun onRestart() {
        super.onRestart()
        setupWidget()
    }

    override fun onPositiveButtonClick() {
        reload()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
