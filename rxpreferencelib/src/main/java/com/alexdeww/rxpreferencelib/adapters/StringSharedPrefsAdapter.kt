package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.rxsharedprefs.RxSharedPreferencesAdapter

class StringSharedPrefsAdapter(
    sharedPreferences: SharedPreferences
) : RxSharedPreferencesAdapter<String>(sharedPreferences) {
    override fun getValue(key: String, defValue: String): String =
        sharedPreferences.getString(key, defValue) ?: defValue

    override fun setValue(key: String, newValue: String) =
        sharedPreferences.edit().apply { putString(key, newValue) }.apply()
}


class StringToIntSharedPrefsAdapter(
    sharedPreferences: SharedPreferences
) : RxSharedPreferencesAdapter<Int>(sharedPreferences) {
    override fun getValue(key: String, defValue: Int): Int =
        sharedPreferences.getString(key, null)?.toIntOrNull() ?: defValue

    override fun setValue(key: String, newValue: Int) =
        sharedPreferences.edit().apply { putString(key, newValue.toString()) }.apply()
}

class StringToLongSharedPrefsAdapter(
    sharedPreferences: SharedPreferences
) : RxSharedPreferencesAdapter<Long>(sharedPreferences) {
    override fun getValue(key: String, defValue: Long): Long =
        sharedPreferences.getString(key, null)?.toLongOrNull() ?: defValue

    override fun setValue(key: String, newValue: Long) =
        sharedPreferences.edit().apply { putString(key, newValue.toString()) }.apply()
}

class StringToFloatSharedPrefsAdapter(
    sharedPreferences: SharedPreferences
) : RxSharedPreferencesAdapter<Float>(sharedPreferences) {
    override fun getValue(key: String, defValue: Float): Float =
        sharedPreferences.getString(key, null)?.toFloatOrNull() ?: defValue

    override fun setValue(key: String, newValue: Float) =
        sharedPreferences.edit().apply { putString(key, newValue.toString()) }.apply()
}