package com.alexdeww.rxpreferencelib

import android.content.SharedPreferences
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class RxPreference<T>(private val sharedPreferences: SharedPreferences,
                      override val key: String,
                      private val defValue: T,
                      private val adapter: PreferenceAdapter<T>,
                      prefKeyChanges: Observable<String>) : Preference<T> {

    private val observable: Observable<T> = prefKeyChanges
            .filter { key == it }
            .startWith("<init>") // Dummy value to trigger initial load.
            .map { value }

    override var value: T
        get() {
            return try {
                adapter.getValue(sharedPreferences, key, defValue)
            } catch (e: Exception) {
                Log.e("RxPreference", "Can't get preference value($key)", e)
                defValue
            }
        }
        set(value) { sharedPreferences.editApply { adapter.setValue(it, key, value) } }

    override fun asObservable(): Observable<T> = observable.observeOn(AndroidSchedulers.mainThread())

    private inline fun <T: SharedPreferences> T.editApply(block: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        try {
            block(editor)
        } finally {
            editor.apply()
        }
    }
}