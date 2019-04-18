package com.kusumi.katsumi.maemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class DictionaryAdapter(
	private val context: Context,
	private val itemClickListener: DictionaryViewHolder.ItemClickListener,
	private val wordTitleList: List<String>,
	private val wordContentList: List<String>
): RecyclerView.Adapter<DictionaryViewHolder>() {

	private var mRecyclerView: RecyclerView? = null

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		super.onAttachedToRecyclerView(recyclerView)
		mRecyclerView = recyclerView
	}

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		super.onDetachedFromRecyclerView(recyclerView)
		mRecyclerView = null
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
		val layoutInflater = LayoutInflater.from(context)
		val view = layoutInflater.inflate(R.layout.snippet_wordcard_item, parent, false)

		view.setOnClickListener { view
			mRecyclerView.let {
				itemClickListener.onItemClick(view, it?.getChildAdapterPosition(view)!!)
			}
		}

		return DictionaryViewHolder(view)
	}

	override fun getItemCount(): Int {
		return wordTitleList.size
	}

	override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
		holder.let {
			it.tvWordTitle.text = wordTitleList[position]
			it.tvWordContent.text = wordContentList[position]
		}
	}
}