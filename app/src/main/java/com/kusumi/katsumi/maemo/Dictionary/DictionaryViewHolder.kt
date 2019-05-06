package com.kusumi.katsumi.maemo.Dictionary

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.snippet_wordlist_item.view.*

class DictionaryViewHolder(view: View): RecyclerView.ViewHolder(view) {

	var appcompat_checkbox = view.appcompat_checkbox!!
	var textview_word_title = view.textview_word_title!!
	var textview_word_content = view.textview_word_content!!
}