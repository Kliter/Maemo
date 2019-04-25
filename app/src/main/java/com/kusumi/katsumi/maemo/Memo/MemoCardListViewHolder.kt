package com.kusumi.katsumi.maemo.Memo

import android.support.design.widget.TextInputEditText
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.snippet_memocard_item.view.*

class MemoCardListViewHolder(view: View): RecyclerView.ViewHolder(view) {

	interface ItemClickListener {
		fun onItemClick(view: View, position: Int)
	}

	var tietFact: TextInputEditText = view.tietCartFactText
	var tietAbstract: TextInputEditText = view.tietCartAbstractText
	var tietDiversion: TextInputEditText = view.tietCartDiversionText
}