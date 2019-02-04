package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.rxsharedprefs.RxSharedPreferencesAdapter

class IntSharedPrefsAdapter(
        sharedPreferences: SharedPreferences
) : RxSharedPreferencesAdapter<Int>(sharedPreferences) {
    override fun getValue(key: String, defValue: Int): Int =
            sharedPreferences.getInt(key, defValue)

    override fun setValue(key: String, newValue: Int) =
            sharedPreferences.edit().apply { putInt(key, newValue) }.apply()
}