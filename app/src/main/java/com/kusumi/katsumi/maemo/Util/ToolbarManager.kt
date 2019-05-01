package com.kusumi.katsumi.maemo.Util


import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

class ToolbarManager {
	companion object {
		fun setupToolbar(activity: AppCompatActivity, toolbar: Toolbar) {
			activity.setSupportActionBar(toolbar)
			activity.supportActionBar?.let {
				it.setDisplayHomeAsUpEnabled(true)
				it.setHomeButtonEnabled(true)
			}
		}
	}

}