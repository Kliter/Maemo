package com.kusumi.katsumi.maemo.Memo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kusumi.katsumi.maemo.BottomNavigationViewManager
import com.kusumi.katsumi.maemo.R
import kotlinx.android.synthetic.main.activity_dictionary.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.selectedItemId = R.id.action_home
        BottomNavigationViewManager.setupBottomNavigationView(
            this,
            bottomNavigationView
        )

//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.rlMainFragmentContainer, MemoFragment())
//            .addToBackStack(null)
//            .commit()
    }



    override fun onRestart() {
        super.onRestart()
        bottomNavigationView.selectedItemId = R.id.action_home
    }
}
