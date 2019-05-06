package com.kusumi.katsumi.maemo.Word

import android.app.Dialog
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.kusumi.katsumi.maemo.DB.WordDBOpenHelper
import com.kusumi.katsumi.maemo.DB.DatabaseHandler
import com.kusumi.katsumi.maemo.Interface.PositiveButtonClickListener
import com.kusumi.katsumi.maemo.Model.Word
import com.kusumi.katsumi.maemo.R
import com.kusumi.katsumi.maemo.Util.StringUtil
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
        val positiveButtonText: String?
        if (isFromEdit) {
            positiveButtonText = getString(R.string.Edit)
            view.text_input_edit_text_word_title.setText(word?.wordTitle, TextView.BufferType.NORMAL)
            view.text_input_edit_text_word_content.setText(word?.wordContent, TextView.BufferType.NORMAL)
        }
        else {
            positiveButtonText = getString(R.string.OK)
        }

        builder.setView(view)
                .setPositiveButton(positiveButtonText) { _, _ ->
                    // Both of the texts are entered.
                    if (StringUtil.isTextEnteredWordDialog(
                            view.text_input_edit_text_word_title.text.toString(),
                            view.text_input_edit_text_word_content.text.toString()
                        )
                    ) {
                        val helper = WordDBOpenHelper(context as Context)
                        val db: SQLiteDatabase = helper.writableDatabase
                        val isQuerySucceed: Boolean
                        if (isFromEdit) {
                           isQuerySucceed = DatabaseHandler.updateWord(
                               db,
                               word?._id!!,
                               view.text_input_edit_text_word_title.text.toString(),
                               view.text_input_edit_text_word_content.text.toString()
                           )
                        }
                        else {
                            isQuerySucceed = DatabaseHandler.insertWord(
                                db,
                                view.text_input_edit_text_word_title.text.toString(),
                                view.text_input_edit_text_word_content.text.toString()
                            )
                        }
                        if (isQuerySucceed) {
                            (activity as PositiveButtonClickListener).onPositiveButtonClick()
                        }
                    }
                }
                .setNegativeButton(R.string.Close) { _, _ ->
                    dismiss()
                }
        return builder.create()
    }
}
