package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.common.SharedPreferenceValueAdapter

object IntSharedPrefsValueAdapter : SharedPreferenceValueAdapter<Int> {

    override fun getValue(
        sharedPreferences: SharedPreferences,
        key: String,
        defValue: Int
    ): Int = sharedPreferences.getInt(key, defValue)

    override fun setValue(
        sharedPreferences: SharedPreferences,
        key: String,
        newValue: Int
    ) {
        sharedPreferences.edit().apply { putInt(key, newValue) }.apply()
    }

}
