package com.alexdeww.rxpreferencelib

import android.content.SharedPreferences

interface PreferenceAdapter<T> {
    fun getValue(sharedPreferences: SharedPreferences, key: String, defValue: T): T
    fun setValue(sharedPreferencesEditor: SharedPreferences.Editor, key: String, newValue: T)
}