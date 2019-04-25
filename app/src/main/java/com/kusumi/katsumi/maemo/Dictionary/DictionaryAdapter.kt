package com.kusumi.katsumi.maemo.Dictionary

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kusumi.katsumi.maemo.R
import com.kusumi.katsumi.maemo.Model.Word
import kotlinx.android.synthetic.main.snippet_wordcard_item.view.*

class DictionaryAdapter(
	private val context: Context,
	private val itemClickListener: DictionaryViewHolder.ItemClickListener,
	val wordList: MutableList<Word>
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
		return wordList.size
	}

	override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
		holder.let {
			it.tvWordTitle.text = wordList[position].wordTitle
			it.tvWordContent.text = wordList[position].wordContent
			it.accbWord.isChecked = wordList[position].isSelected
			it.accbWord.tag = position
			it.accbWord.setOnClickListener {
				val pos: Int = it.accbWord.tag as Int
				wordList[pos].isSelected = !wordList[pos].isSelected
			}
		}
	}
}