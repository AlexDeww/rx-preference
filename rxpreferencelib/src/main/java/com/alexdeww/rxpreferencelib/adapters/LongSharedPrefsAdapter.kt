package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.rxsharedprefs.RxSharedPreferencesAdapter

class LongSharedPrefsAdapter(
        sharedPreferences: SharedPreferences
) : RxSharedPreferencesAdapter<Long>(sharedPreferences) {
    override fun getValue(key: String, defValue: Long): Long =
            sharedPreferences.getLong(key, defValue)

    override fun setValue(key: String, newValue: Long) =
            sharedPreferences.edit().apply { putLong(key, newValue) }.apply()
}