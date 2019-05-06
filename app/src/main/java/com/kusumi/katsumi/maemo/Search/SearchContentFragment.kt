package com.kusumi.katsumi.maemo.Search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusumi.katsumi.maemo.R

class SearchContentFragment : Fragment() {

	private var currentFragmentNum: Int? = 0

	companion object {

		private const val ARGS_SECTION_NUM = "section_num"

		fun newInstance(sectionNum: Int): SearchContentFragment {
			val fragment = SearchContentFragment()
			val arguments = Bundle()
			arguments.putInt(ARGS_SECTION_NUM, sectionNum)
			fragment.arguments = arguments
			return fragment
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		currentFragmentNum = arguments?.getInt(ARGS_SECTION_NUM)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_search_content, container, false)
	}
}