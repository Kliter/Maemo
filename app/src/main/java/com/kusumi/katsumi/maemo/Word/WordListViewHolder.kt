package com.kusumi.katsumi.maemo.Word

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.snippet_wordcard_item.view.*

class WordListViewHolder(view: View): RecyclerView.ViewHolder(view) {

	interface ItemClickListener {
		fun onItemClick(view: View, position: Int)
	}
	var accbWord = view.accbWord!!
	var tvWordTitle = view.tvWordTitle!!
	var tvWordContent = view.tvWordContent!!
}