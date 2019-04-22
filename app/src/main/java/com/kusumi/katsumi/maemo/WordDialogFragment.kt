package com.kusumi.katsumi.maemo

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_word_dialog.view.*

class WordDialogFragment: DialogFragment() {

    private val WORD = "WORD"
    private var word: Word? = null

    private var isFromEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        word = arguments?.getSerializable(WORD) as Word?
        if (word != null) {
            isFromEdit = true
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!)

        val inflater: LayoutInflater = activity?.layoutInflater!!
        val view: View = inflater.inflate(R.layout.fragment_word_dialog, null)
        var positiveButtonText: String? = null
        if (isFromEdit) {
            positiveButtonText = getString(R.string.Edit)
            view.tietWordTitle.setText(word?.wordTitle, TextView.BufferType.NORMAL)
            view.tietWordContent.setText(word?.wordContent, TextView.BufferType.NORMAL)
        }
        else {
            positiveButtonText = getString(R.string.OK)
        }

        builder.setView(view)
                .setPositiveButton(positiveButtonText) { _, _ ->
                    // Both of the texts are entered.
                    if (StringUtil.isTextEnteredWordDialog(view.tietWordTitle.text.toString(), view.tietWordContent.text.toString())) {
                        val helper = DictionaryDBOpenHelper(context as Context)
                        val db: SQLiteDatabase = helper.writableDatabase
                        var isQuerySucceed = false
                        if (isFromEdit) {
                           isQuerySucceed =  DatabaseHandler
                                   .updateWord(
                                       db,
                                       word?._id!!,
                                       view.tietWordTitle.text.toString(),
                                       view.tietWordContent.text.toString()
                                    )
                        }
                        else {
                            isQuerySucceed = DatabaseHandler
                                    .insertWord(
                                            db,
                                            view.tietWordTitle.text.toString(),
                                            view.tietWordContent.text.toString()
                                    )
                        }
                        if (isQuerySucceed) {
                            (activity as Activity).finish()
                            val intent = Intent()
                            intent.setClass(activity as Context, DictionaryActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
                .setNegativeButton(R.string.Close) { _, _ ->
                    dismiss()
                }
        return builder.create()
    }
}