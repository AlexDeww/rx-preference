package com.alexdeww.rxpreferencelib.rxsharedprefs

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.RxPreference
import com.alexdeww.rxpreferencelib.adapters.*
import com.alexdeww.rxpreferencelib.common.RxPreferenceAdapter
import io.reactivex.Observable

abstract class RxSharedPreferences(
        val sharedPreferences: SharedPreferences
) {
    protected val keyChangesObservable: Observable<String> = Observable.create<String> { emitter ->
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            emitter.onNext(key ?: "")
        }
        emitter.setCancellable { sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener) }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }.share()
    private val adapters by lazy { Adapters(sharedPreferences) }

    open fun <T> getPreference(key: String, defValue: T, adapter: RxPreferenceAdapter<T>): RxPreference<T> =
            RxPreferenceImpl(key, defValue, adapter, keyChangesObservable)

    fun getString(key: String, defValue: String = "") = getPreference(key, defValue, adapters.STRING)

    fun getInt(key: String, defValue: Int = 0) = getPreference(key, defValue, adapters.INT)

    fun getLong(key: String, defValue: Long = 0) = getPreference(key, defValue, adapters.LONG)

    fun getFloat(key: String, defValue: Float = 0f) = getPreference(key, defValue, adapters.FLOAT)

    fun getBoolean(key: String, defValue: Boolean = false) = getPreference(key, defValue, adapters.BOOLEAN)

    fun getDouble(key: String, defValue: Double = 0.0) = getPreference(key, defValue, adapters.DOUBLE)

    protected class Adapters(sharedPreferences: SharedPreferences) {
        val STRING = StringSharedPrefsAdapter(sharedPreferences)
        val INT = IntSharedPrefsAdapter(sharedPreferences)
        val LONG = LongSharedPrefsAdapter(sharedPreferences)
        val FLOAT = FloatSharedPrefsAdapter(sharedPreferences)
        val BOOLEAN = BooleanSharedPrefsAdapter(sharedPreferences)
        val DOUBLE = DoubleSharedPrefsAdapter(sharedPreferences)
    }
}