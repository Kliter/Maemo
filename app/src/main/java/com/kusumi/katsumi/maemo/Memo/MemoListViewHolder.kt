package com.kusumi.katsumi.maemo.Memo

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.snippet_memolist_item.view.*

class MemoListViewHolder(view: View): RecyclerView.ViewHolder(view) {

	interface ItemClickListener {
		fun onItemClick(view: View, position: Int)
	}

	var accbMemo = view.accbMemo!!
	var tvFact = view.tvFact!!
	var tvAbstract = view.tvAbstract!!
	var tvDiversion = view.tvDiversion!!
}