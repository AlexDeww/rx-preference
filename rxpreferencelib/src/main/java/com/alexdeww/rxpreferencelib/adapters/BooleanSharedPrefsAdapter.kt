package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.common.SharedPreferenceValueAdapter

object BooleanSharedPrefsValueAdapter : SharedPreferenceValueAdapter<Boolean> {

    override fun getValue(
        sharedPreferences: SharedPreferences,
        key: String,
        defValue: Boolean
    ): Boolean = sharedPreferences.getBoolean(key, defValue)

    override fun setValue(
        sharedPreferences: SharedPreferences,
        key: String,
        newValue: Boolean
    ) {
        sharedPreferences.edit().apply { putBoolean(key, newValue) }.apply()
    }

}
