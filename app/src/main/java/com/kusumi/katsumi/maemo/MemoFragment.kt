package com.kusumi.katsumi.maemo

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_memo.*

class MemoFragment: Fragment(), MemoCardListViewHolder.ItemClickListener {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view: View = inflater.inflate(R.layout.fragment_memo, container, false)

		// Create memo contents list.
		val factList = listOf<TextInputEditText>(
			TextInputEditText(context),
			TextInputEditText(context),
			TextInputEditText(context),
			TextInputEditText(context),
			TextInputEditText(context)
		)
		val abstractList = listOf<TextInputEditText>(
			TextInputEditText(context),
			TextInputEditText(context),
			TextInputEditText(context),
			TextInputEditText(context),
			TextInputEditText(context)
		)
		val diversionList = listOf<TextInputEditText>(
			TextInputEditText(context),
			TextInputEditText(context),
			TextInputEditText(context),
			TextInputEditText(context),
			TextInputEditText(context)
		)

		val rvMemoCardContainer = view.findViewById<RecyclerView>(R.id.rvMemoCardContainer)
		rvMemoCardContainer.adapter = MemoCardListAdapter(context!!, this, factList, abstractList, diversionList)
		rvMemoCardContainer.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


		return view
	}

	override fun onItemClick(view: View, position: Int) {
		Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
	}
}