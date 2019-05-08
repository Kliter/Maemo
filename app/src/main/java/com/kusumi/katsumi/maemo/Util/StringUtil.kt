package com.kusumi.katsumi.maemo.Util

class StringUtil {
    companion object {
        fun isTextEnteredMemoDialog(text_input_edit_text_memo_fact: String?, text_input_edit_memo_abstract: String?, text_input_edit_text_memo_diversion: String?): Boolean {
            return text_input_edit_text_memo_fact != null && text_input_edit_memo_abstract != null && text_input_edit_text_memo_diversion != null &&
                    text_input_edit_text_memo_fact != "" && text_input_edit_memo_abstract != "" && text_input_edit_text_memo_diversion != ""
        }
    }
}