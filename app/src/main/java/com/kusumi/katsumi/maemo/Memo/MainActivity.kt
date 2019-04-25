package com.kusumi.katsumi.maemo.Memo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.kusumi.katsumi.maemo.BottomNavigationViewManager
import com.kusumi.katsumi.maemo.R
import kotlinx.android.synthetic.main.activity_dictionary.bottomNavigationView
import kotlinx.android.synthetic.main.activity_dictionary.fabInsertWord
import kotlinx.android.synthetic.main.snippet_toolbar.*

class MainActivity : AppCompatActivity() {

    private var dbOpenHelper: MemoDBOpenHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupWidget()
    }

    private fun setupWidget() {

        setupToolbar()
        setupBottomNavigationView()

        fabInsertWord.setOnClickListener {
            //Todo: Implement MemoDialogFragment
            MemoDialogFragment().show(supportFragmentManager, "launch")
        }

        dbOpenHelper = MemoDBOpenHelper(this)
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = appbar
        this.setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.selectedItemId = R.id.action_home
        BottomNavigationViewManager.setupBottomNavigationView(
            this,
            bottomNavigationView
        )
    }

    override fun onRestart() {
        super.onRestart()
        bottomNavigationView.selectedItemId = R.id.action_home
    }
}
