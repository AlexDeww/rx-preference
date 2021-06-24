package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.common.SharedPreferenceValueAdapter

object FloatSharedPrefsValueAdapter : SharedPreferenceValueAdapter<Float> {

    override fun getValue(
        sharedPreferences: SharedPreferences,
        key: String,
        defValue: Float
    ): Float = sharedPreferences.getFloat(key, defValue)

    override fun setValue(
        sharedPreferences: SharedPreferences,
        key: String,
        newValue: Float
    ) {
        sharedPreferences.edit().apply { putFloat(key, newValue) }.apply()
    }

}
