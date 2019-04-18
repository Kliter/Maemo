package com.kusumi.katsumi.maemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dictionary.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.selectedItemId = R.id.action_home
        BottomNavigationViewManager.setupBottomNavigationView(this, bottomNavigationView)

//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.rlMainFragmentContainer, MemoFragment())
//            .addToBackStack(null)
//            .commit()
    }
}
