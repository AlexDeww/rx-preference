package com.alexdeww.rxpreferencelib

import android.content.SharedPreferences

class LambdaPreferenceAdapter<T>(
        private val writer: (SharedPreferences.Editor, String, T) -> Unit,
        private val reader: (SharedPreferences, String, T) -> T
) : PreferenceAdapter<T> {
    override fun getValue(sharedPreferences: SharedPreferences, key: String, defValue: T): T =
            reader(sharedPreferences, key, defValue)
    override fun setValue(sharedPreferencesEditor: SharedPreferences.Editor, key: String, newValue: T) {
        writer(sharedPreferencesEditor, key, newValue)
    }
}