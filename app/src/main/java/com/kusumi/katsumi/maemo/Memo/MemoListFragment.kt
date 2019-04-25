package com.kusumi.katsumi.maemo.Memo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusumi.katsumi.maemo.R

class MemoListFragment: Fragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view: View = inflater.inflate(R.layout.fragment_memolist, container, false)

		// Create note titles list.


		return view
	}
}