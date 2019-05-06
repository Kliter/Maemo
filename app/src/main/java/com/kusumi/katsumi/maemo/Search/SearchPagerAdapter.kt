package com.kusumi.katsumi.maemo.Search

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kusumi.katsumi.maemo.Util.Constants.*
import com.kusumi.katsumi.maemo.Util.Constants.Companion.MEMO
import com.kusumi.katsumi.maemo.Util.Constants.Companion.MEMO_SECTION_NUM
import com.kusumi.katsumi.maemo.Util.Constants.Companion.WORD
import com.kusumi.katsumi.maemo.Util.Constants.Companion.WORD_SECTION_NUM

class SearchPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

	companion object {
		private const val TOTAL_PAGE_NUM = 2
	}

	var mMemoFragment: Fragment? = null
	var mWordFragment: Fragment? = null

	override fun getItem(position: Int): Fragment? {
		when (position) {
			0 -> {
				mMemoFragment = SearchContentFragment.newInstance(MEMO_SECTION_NUM)
				return mMemoFragment
			}
			1 -> {
				mWordFragment = SearchContentFragment.newInstance(WORD_SECTION_NUM)
				return mWordFragment
			}
		}
		return null
	}

	override fun getCount(): Int {
		return TOTAL_PAGE_NUM
	}

	override fun getPageTitle(position: Int): CharSequence? {
		when (position) {
			0 -> {
				return MEMO
			}
			1 -> {
				return WORD
			}
		}
		return null
	}
}