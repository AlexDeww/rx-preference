package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.rxsharedprefs.RxSharedPreferencesAdapter

class BooleanSharedPrefsAdapter(
        sharedPreferences: SharedPreferences
) : RxSharedPreferencesAdapter<Boolean>(sharedPreferences) {
    override fun getValue(key: String, defValue: Boolean): Boolean =
            sharedPreferences.getBoolean(key, defValue)

    override fun setValue(key: String, newValue: Boolean) =
            sharedPreferences.edit().apply { putBoolean(key, newValue) }.apply()
}