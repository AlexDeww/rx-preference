package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.rxsharedprefs.RxSharedPreferencesAdapter

class FloatSharedPrefsAdapter(
        sharedPreferences: SharedPreferences
) : RxSharedPreferencesAdapter<Float>(sharedPreferences) {
    override fun getValue(key: String, defValue: Float): Float =
            sharedPreferences.getFloat(key, defValue)

    override fun setValue(key: String, newValue: Float) =
            sharedPreferences.edit().apply { putFloat(key, newValue) }.apply()
}