package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.common.SharedPreferenceValueAdapter

object LongSharedPrefsValueAdapter : SharedPreferenceValueAdapter<Long> {

    override fun getValue(
        sharedPreferences: SharedPreferences,
        key: String,
        defValue: Long
    ): Long = sharedPreferences.getLong(key, defValue)

    override fun setValue(
        sharedPreferences: SharedPreferences,
        key: String,
        newValue: Long
    ) {
        sharedPreferences.edit().apply { putLong(key, newValue) }.apply()
    }

}
