package com.kusumi.katsumi.maemo.Util

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.kusumi.katsumi.maemo.Word.WordActivity
import com.kusumi.katsumi.maemo.Memo.MainActivity
import com.kusumi.katsumi.maemo.R
import com.kusumi.katsumi.maemo.Search.SearchActivity

class BottomNavigationViewManager {
	companion object {
		fun setupBottomNavigationView(
			activity: AppCompatActivity,
			bottom_navigation_view: BottomNavigationView
		) {

			bottom_navigation_view.setOnNavigationItemSelectedListener {
				val intent = Intent()
				when (it.itemId) {
					R.id.action_home -> {
						intent.setClass(activity, MainActivity::class.java)
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
						activity.startActivity(intent)
						return@setOnNavigationItemSelectedListener true
					}
					R.id.action_word -> {
						intent.setClass(activity, WordActivity::class.java)
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
						activity.startActivity(intent)
						return@setOnNavigationItemSelectedListener true
					}
					R.id.action_search -> {
						intent.setClass(activity, SearchActivity::class.java)
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
						activity.startActivity(intent)
						return@setOnNavigationItemSelectedListener true
					}
				}
				return@setOnNavigationItemSelectedListener false
			}
		}
	}
}