package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.common.SharedPreferenceValueAdapter

object DoubleSharedPrefsValueAdapter : SharedPreferenceValueAdapter<Double> {

    override fun getValue(
        sharedPreferences: SharedPreferences,
        key: String,
        defValue: Double
    ): Double = java.lang.Double.longBitsToDouble(
        sharedPreferences.getLong(key, java.lang.Double.doubleToRawLongBits(defValue))
    )

    override fun setValue(
        sharedPreferences: SharedPreferences,
        key: String,
        newValue: Double
    ) {
        sharedPreferences.edit().apply {
            putLong(key, java.lang.Double.doubleToRawLongBits(newValue))
        }.apply()
    }

}
