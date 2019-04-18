package com.kusumi.katsumi.maemo

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.snippet_wordcard_item.view.*

class DictionaryViewHolder(view: View): RecyclerView.ViewHolder(view) {

	interface ItemClickListener {
		fun onItemClick(view: View, position: Int)
	}

	var tvWordTitle = view.tvWordTitle!!
	var tvWordContent = view.tvWordContent!!
}