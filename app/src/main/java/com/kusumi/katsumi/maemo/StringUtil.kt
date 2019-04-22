package com.kusumi.katsumi.maemo

import android.support.design.widget.TextInputEditText

class StringUtil {
    companion object {
        fun isTextEnteredWordDialog(tietWordTitle: String?, tietWordContent: String?): Boolean {
            return tietWordTitle != null && tietWordContent != null && tietWordTitle != "" && tietWordContent != ""
        }
    }
}