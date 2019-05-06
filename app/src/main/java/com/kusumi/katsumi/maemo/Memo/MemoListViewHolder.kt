package com.kusumi.katsumi.maemo.Memo

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.snippet_memolist_item.view.*

class MemoListViewHolder(view: View): RecyclerView.ViewHolder(view) {

	var appcompat_checkbox = view.appcompat_checkbox!!
	var textview_memo_fact = view.textview_memo_fact!!
	var textview_memo_abstract = view.textview_memo_abstract!!
	var textview_memo_diversion = view.textview_memo_diversion!!
	var textview_update_time = view.textview_update_time!!

}