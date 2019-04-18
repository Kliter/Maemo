package com.kusumi.katsumi.maemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dictionary.*

class DictionaryActivity(): AppCompatActivity(), DictionaryViewHolder.ItemClickListener {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_dictionary)
		bottomNavigationView.selectedItemId = R.id.action_dictionary
		BottomNavigationViewManager.setupBottomNavigationView(this, bottomNavigationView)

		val wordTitleList = listOf<String>(
			"TEST1",
			"TEST2",
			"TEST3",
			"TEST4",
			"TEST5"
		)

		val wordContentList = listOf<String>(
			"test1",
			"test2",
			"test3",
			"test4",
			"test5"
		)

		rvWordCardContainer.adapter = DictionaryAdapter(this, this, wordTitleList, wordContentList)
		rvWordCardContainer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
	}

	override fun onItemClick(view: View, position: Int) {
		Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
	}
}