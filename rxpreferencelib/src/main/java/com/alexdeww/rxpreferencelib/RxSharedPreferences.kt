package com.alexdeww.rxpreferencelib

import android.content.SharedPreferences
import io.reactivex.Observable

@Suppress("MemberVisibilityCanPrivate")
class RxSharedPreferences(val sharedPreferences: SharedPreferences) {

    private val adapters = Adapters(sharedPreferences)
    private val keyChangesObservable: Observable<String> = Observable.create<String> {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key -> it.onNext(key ?: "") }
        it.setCancellable { sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener) }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }.share()

    fun <T> getPreference(key: String, defValue: T, adapter: PreferenceAdapter<T>): Preference<T> =
            RxPreference(sharedPreferences, key, defValue, adapter, keyChangesObservable)

    fun getString(key: String, defValue: String = ""): Preference<String> = getPreference(key, defValue, adapters.STRING)

    fun getInt(key: String, defValue: Int = 0): Preference<Int> = getPreference(key, defValue, adapters.INT)

    fun getLong(key: String, defValue: Long = 0): Preference<Long> = getPreference(key, defValue, adapters.LONG)

    fun getFloat(key: String, defValue: Float = 0.toFloat()): Preference<Float> = getPreference(key, defValue, adapters.FLOAT)

    fun getBoolean(key: String, defValue: Boolean = false): Preference<Boolean> = getPreference(key, defValue, adapters.BOOLEAN)

    fun getDouble(key: String, defValue: Double = 0.0): Preference<Double> = getPreference(key, defValue, adapters.DOUBLE)

    private class Adapters(sharedPreferences: SharedPreferences) {
        val STRING = ShortLambdaAdapter(sharedPreferences.edit()::putString, sharedPreferences::getString)
        val INT = ShortLambdaAdapter(sharedPreferences.edit()::putInt, sharedPreferences::getInt)
        val LONG = ShortLambdaAdapter(sharedPreferences.edit()::putLong, sharedPreferences::getLong)
        val FLOAT = ShortLambdaAdapter(sharedPreferences.edit()::putFloat, sharedPreferences::getFloat)
        val BOOLEAN = ShortLambdaAdapter(sharedPreferences.edit()::putBoolean, sharedPreferences::getBoolean)
        val DOUBLE = LambdaPreferenceAdapter<Double>(
                { e, k, v -> e.putLong(k, java.lang.Double.doubleToRawLongBits(v)) },
                { s, k, v -> java.lang.Double.longBitsToDouble(s.getLong(k, java.lang.Double.doubleToRawLongBits(v))) }
        )
    }

    private class ShortLambdaAdapter<T>(
            private val writer: (String, T) -> SharedPreferences.Editor,
            private val reader: (String, T) -> T
    ) : PreferenceAdapter<T> {
        override fun getValue(sharedPreferences: SharedPreferences, key: String, defValue: T): T =
                reader(key, defValue)
        override fun setValue(sharedPreferencesEditor: SharedPreferences.Editor, key: String, newValue: T) {
            writer(key, newValue).apply() //!!!
        }
    }

}