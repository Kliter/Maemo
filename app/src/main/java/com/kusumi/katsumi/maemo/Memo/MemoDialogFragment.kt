package com.kusumi.katsumi.maemo.Memo

import android.app.Dialog
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.kusumi.katsumi.maemo.Model.Memo
import com.kusumi.katsumi.maemo.R
import com.kusumi.katsumi.maemo.Util.DatabaseHandler
import com.kusumi.katsumi.maemo.Util.StringUtil
import com.kusumi.katsumi.maemo.DB.WordDBOpenHelper
import kotlinx.android.synthetic.main.fragment_memo_dialog.view.*

class MemoDialogFragment: DialogFragment() {

	private val MEMO = "MEMO"
	private var memo: Memo? = null

	private var isFromEdit: Boolean = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		memo = arguments?.getSerializable(MEMO) as Memo?
		if (memo != null) {
			isFromEdit = true
		}
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val builder = AlertDialog.Builder(context!!)

		val inflater: LayoutInflater = activity?.layoutInflater!!
		val view: View = inflater.inflate(R.layout.fragment_memo_dialog, null)
		var positiveButtonText: String? = null
		if (isFromEdit) {
			positiveButtonText = getString(R.string.Edit)
			view.tietMemoFact.setText(memo?.factText, TextView.BufferType.NORMAL)
			view.tietMemoAbstract.setText(memo?.abstractText, TextView.BufferType.NORMAL)
			view.tietMemoDiversion.setText(memo?.diversionText, TextView.BufferType.NORMAL)
		}
		else {
			positiveButtonText = getString(R.string.OK)
		}

		builder.setView(view).setPositiveButton(positiveButtonText) { _, _ ->
			// Both of the texts are entered.
			if (StringUtil.isTextEnteredMemoDialog(
					view.tietMemoFact.text.toString(),
					view.tietMemoAbstract.text.toString(),
					view.tietMemoDiversion.text.toString()
				)
			) {
				val helper = WordDBOpenHelper(context as Context)
				val db: SQLiteDatabase = helper.writableDatabase

				var isQuerySucceed = false
				if (isFromEdit) {
					DatabaseHandler.updateMemo(
						db,
						memo?._id!!,
						view.tietMemoFact.text.toString(),
						view.tietMemoAbstract.text.toString(),
						view.tietMemoDiversion.text.toString()
					)
				}
				else {
					isQuerySucceed = DatabaseHandler.insertMemo(
						db,
						view.tietMemoFact.text.toString(),
						view.tietMemoAbstract.text.toString(),
						view.tietMemoDiversion.text.toString()
					)
				}

				if (isQuerySucceed) {
					(activity as MainActivity).reload()
				}
			}
		}
		.setNegativeButton(R.string.Close) { _, _ ->
			dismiss()
		}

		return builder.create()
	}
}