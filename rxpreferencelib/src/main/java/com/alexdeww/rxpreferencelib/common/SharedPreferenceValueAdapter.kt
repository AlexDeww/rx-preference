package com.alexdeww.rxpreferencelib.common

import android.content.SharedPreferences

interface SharedPreferenceValueAdapter<T : Any> {
    fun getValue(sharedPreferences: SharedPreferences, key: String, defValue: T): T
    fun setValue(sharedPreferences: SharedPreferences, key: String, newValue: T)
}
