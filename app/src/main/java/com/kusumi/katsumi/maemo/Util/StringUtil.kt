package com.kusumi.katsumi.maemo.Util

class StringUtil {

    companion object {

        fun isTextEnteredWordDialog(tietWordTitle: String?, tietWordContent: String?): Boolean {
            return tietWordTitle != null && tietWordContent != null && tietWordTitle != "" && tietWordContent != ""
        }

        fun isTextEnteredMemoDialog(tietMemoFact: String?, tietMemoAbstract: String?, tietMemoDiversion: String?): Boolean {
            return tietMemoFact != null && tietMemoAbstract != null && tietMemoDiversion != null &&
                    tietMemoFact != "" && tietMemoAbstract != "" && tietMemoDiversion != ""
        }
    }
}