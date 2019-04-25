package com.kusumi.katsumi.maemo

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.kusumi.katsumi.maemo.Dictionary.DictionaryActivity
import com.kusumi.katsumi.maemo.Memo.MainActivity

class BottomNavigationViewManager {
	companion object {
		fun setupBottomNavigationView(
			activity: AppCompatActivity,
			bottomNavigationView: BottomNavigationView
		) {

			bottomNavigationView.setOnNavigationItemSelectedListener {
				val intent = Intent()
				when (it.itemId) {
					R.id.action_home -> {
						intent.setClass(activity, MainActivity::class.java)
						activity.startActivity(intent)
						return@setOnNavigationItemSelectedListener true
					}
					R.id.action_dictionary -> {
						intent.setClass(activity, DictionaryActivity::class.java)
						activity.startActivity(intent)
						return@setOnNavigationItemSelectedListener true
					}
					R.id.action_search -> {
						return@setOnNavigationItemSelectedListener true
					}
					R.id.action_settings -> {
						return@setOnNavigationItemSelectedListener true
					}
				}
				return@setOnNavigationItemSelectedListener false
			}
		}
	}
}