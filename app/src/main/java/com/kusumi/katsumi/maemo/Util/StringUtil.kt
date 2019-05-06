package com.kusumi.katsumi.maemo.Util

class StringUtil {

    companion object {

        fun isTextEnteredWordDialog(text_input_edit_text_word_title: String?, text_input_edit_text_word_content: String?): Boolean {
            return text_input_edit_text_word_title != null && text_input_edit_text_word_content != null && text_input_edit_text_word_title != "" && text_input_edit_text_word_content != ""
        }

        fun isTextEnteredMemoDialog(text_input_edit_text_memo_fact: String?, text_input_edit_memo_abstract: String?, text_input_edit_text_memo_diversion: String?): Boolean {
            return text_input_edit_text_memo_fact != null && text_input_edit_memo_abstract != null && text_input_edit_text_memo_diversion != null &&
                    text_input_edit_text_memo_fact != "" && text_input_edit_memo_abstract != "" && text_input_edit_text_memo_diversion != ""
        }
    }
}