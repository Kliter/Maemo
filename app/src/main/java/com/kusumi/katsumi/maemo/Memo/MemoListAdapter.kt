package com.kusumi.katsumi.maemo.Memo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kusumi.katsumi.maemo.Interface.ItemClickListener
import com.kusumi.katsumi.maemo.Model.Memo
import com.kusumi.katsumi.maemo.R
import kotlinx.android.synthetic.main.snippet_memolist_item.view.*

class MemoListAdapter(
	private val context: Context,
	private val itemClickListener: ItemClickListener,
	val memoList: MutableList<Memo>
): RecyclerView.Adapter<MemoListViewHolder>() {

	private var mRecyclerView: RecyclerView? = null

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		super.onAttachedToRecyclerView(recyclerView)
		this.mRecyclerView = recyclerView
	}

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		super.onDetachedFromRecyclerView(recyclerView)
		this.mRecyclerView = null
	}

	override fun onCreateViewHolder(parent: ViewGroup, position: Int): MemoListViewHolder {
		val layoutInflater = LayoutInflater.from(context)
		val view = layoutInflater.inflate(R.layout.snippet_memolist_item, parent, false)
		view.setOnClickListener {
			mRecyclerView.let {
				itemClickListener.onItemClick(view, it?.getChildAdapterPosition(view)!!)
			}
		}
		return MemoListViewHolder(view)
	}

	override fun getItemCount(): Int {
		return memoList.size
	}

	override fun onBindViewHolder(holder: MemoListViewHolder, position: Int) {
		holder.let{
			it.textview_memo_fact.text = memoList[position].factText
			it.textview_memo_abstract.text = memoList[position].abstractText
			it.textview_memo_diversion.text = memoList[position].diversionText
			it.appcompat_checkbox.tag = position
			it.appcompat_checkbox.setOnClickListener {
				val pos: Int = it.appcompat_checkbox.tag as Int
				memoList[pos].isSelected = !memoList[pos].isSelected
			}
		}
	}
}