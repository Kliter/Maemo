package com.kusumi.katsumi.maemo.Memo

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kusumi.katsumi.maemo.DB.MemoDBOpenHelper
import com.kusumi.katsumi.maemo.Util.BottomNavigationViewManager
import com.kusumi.katsumi.maemo.Model.Memo
import com.kusumi.katsumi.maemo.R
import com.kusumi.katsumi.maemo.Util.ToolbarManager
import com.kusumi.katsumi.maemo.Util.DatabaseHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.snippet_toolbar.*

class MainActivity : AppCompatActivity(), MemoListViewHolder.ItemClickListener {

    private lateinit var dbOpenHelper: MemoDBOpenHelper
    private lateinit var memoReadableDB: SQLiteDatabase
    private val TABLE_NAME = "Memo"
    private val TABLE_EXISTS = "1"

    private lateinit var memoList: MutableList<Memo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupWidget()
    }

    private fun setupWidget() {
        ToolbarManager.setupToolbar(this, appbar)
        setupBottomNavigationView()
        memoList = mutableListOf()

        fabInsertWord.setOnClickListener {
            MemoDialogFragment().show(supportFragmentManager, "launch")
        }

        dbOpenHelper = MemoDBOpenHelper(this)
        memoReadableDB = dbOpenHelper.readableDatabase

        val cursor: Cursor = DatabaseHandler.existsTable(memoReadableDB, TABLE_NAME)
        cursor.moveToFirst()
        if (cursor.getString(0) == TABLE_EXISTS) {
            setupMemoList()
        }

        rvMemoCardContainer.adapter = MemoListAdapter(this, this, memoList)
        rvMemoCardContainer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.selectedItemId = R.id.action_home
        BottomNavigationViewManager.setupBottomNavigationView(this, bottomNavigationView
        )
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
            memoList.add(memo)
            cursor.moveToNext()
        }
        cursor.close()
    }

    override fun onItemClick(view: View, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        setupWidget()
    }
}
