package com.kusumi.katsumi.maemo.Util

import java.text.SimpleDateFormat
import java.util.*


class DateConverter {
	companion object {

		fun getCurrentDate(): Long {
			return System.currentTimeMillis()
		}

		fun convertLongToString(dateLong: Long?): String {
			val date = Date(dateLong!!)
			val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
			return simpleDateFormat.format(date)
		}
	}
}