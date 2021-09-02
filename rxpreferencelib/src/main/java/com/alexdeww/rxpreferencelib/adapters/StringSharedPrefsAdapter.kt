package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.common.SharedPreferenceValueAdapter

object StringSharedPrefsValueAdapter : SharedPreferenceValueAdapter<String> {

    override fun getValue(
        sharedPreferences: SharedPreferences,
        key: String,
        defValue: String
    ): String = sharedPreferences.getString(key, defValue) ?: defValue

    override fun setValue(
        sharedPreferences: SharedPreferences,
        key: String,
        newValue: String
    ) {
        sharedPreferences.edit().apply { putString(key, newValue) }.apply()
    }

}

object StringAsIntSharedPrefsValueAdapter : SharedPreferenceValueAdapter<Int> {

    override fun getValue(
        sharedPreferences: SharedPreferences,
        key: String,
        defValue: Int
    ): Int = sharedPreferences.getString(key, null)?.toIntOrNull() ?: defValue

    override fun setValue(
        sharedPreferences: SharedPreferences,
        key: String,
        newValue: Int
    ) {
        sharedPreferences.edit().apply { putString(key, newValue.toString()) }.apply()
    }

}

object StringAsLongSharedPrefsValueAdapter : SharedPreferenceValueAdapter<Long> {

    override fun getValue(
        sharedPreferences: SharedPreferences,
        key: String,
        defValue: Long
    ): Long = sharedPreferences.getString(key, null)?.toLongOrNull() ?: defValue

    override fun setValue(
        sharedPreferences: SharedPreferences,
        key: String,
        newValue: Long
    ) {
        sharedPreferences.edit().apply { putString(key, newValue.toString()) }.apply()
    }

}

object StringAsFloatSharedPrefsAdapter : SharedPreferenceValueAdapter<Float> {

    override fun getValue(
        sharedPreferences: SharedPreferences,
        key: String,
        defValue: Float
    ): Float = sharedPreferences.getString(key, null)?.toFloatOrNull() ?: defValue

    override fun setValue(
        sharedPreferences: SharedPreferences,
        key: String,
        newValue: Float
    ) {
        sharedPreferences.edit().apply { putString(key, newValue.toString()) }.apply()
    }

}
