package com.kusumi.katsumi.maemo.Memo

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kusumi.katsumi.maemo.R

class MemoCardListAdapter(
	private val context: Context,
	private val itemClickListener: MemoCardListViewHolder.ItemClickListener,
	private val factTextList: List<TextInputEditText>,
	private val abstractTextList: List<TextInputEditText>,
	private val diversionTextList: List<TextInputEditText>
): RecyclerView.Adapter<MemoCardListViewHolder>() {

	private var mRecyclerView: RecyclerView? = null

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		super.onAttachedToRecyclerView(recyclerView)
		mRecyclerView = recyclerView
	}

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		super.onDetachedFromRecyclerView(recyclerView)
		mRecyclerView = null
	}

	override fun onBindViewHolder(holder: MemoCardListViewHolder, position: Int) {
		holder.let {
			it.tietFact = factTextList[position]
			it.tietAbstract = abstractTextList[position]
			it.tietDiversion = diversionTextList[position]
		}
	}

	override fun getItemCount(): Int {
		return factTextList.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoCardListViewHolder {
		val layoutInflater = LayoutInflater.from(context)
		val mView = layoutInflater.inflate(R.layout.snippet_memocard_item, parent, false)

		mView.setOnClickListener { view ->
			mRecyclerView.let {
				itemClickListener.onItemClick(view, it?.getChildAdapterPosition(view)!!)
			}
		}

		return MemoCardListViewHolder(mView)
	}
}