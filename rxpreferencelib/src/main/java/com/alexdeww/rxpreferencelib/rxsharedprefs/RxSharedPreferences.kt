package com.alexdeww.rxpreferencelib.rxsharedprefs

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import com.alexdeww.rxpreferencelib.RxPreference
import com.alexdeww.rxpreferencelib.adapters.*
import com.alexdeww.rxpreferencelib.common.SharedPreferenceValueAdapter
import io.reactivex.rxjava3.core.Observable

abstract class RxSharedPreferences(val sharedPreferences: SharedPreferences) {

    private val keyChangesObservable: Observable<String> = Observable
        .create<String> { emitter ->
            val listener = OnSharedPreferenceChangeListener { _, key ->
                emitter.onNext(key ?: "")
            }
            emitter.setCancellable {
                sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
            }
            sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        }
        .share()

    protected fun <T : Any> customValue(
        key: String,
        defValue: T,
        valueAdapter: SharedPreferenceValueAdapter<T>
    ): RxPreference<T> = RxPreferenceImpl(
        key = key,
        defValue = defValue,
        adapter = valueAdapter,
        sharedPreferences = sharedPreferences,
        prefKeyChangesObservable = keyChangesObservable
    )

    protected fun stringValue(
        key: String,
        defValue: String = ""
    ): RxPreference<String> = customValue(
        key = key,
        defValue = defValue,
        valueAdapter = StringSharedPrefsValueAdapter
    )

    protected fun stringAsIntValue(
        key: String,
        defValue: Int = 0
    ): RxPreference<Int> = customValue(
        key = key,
        defValue = defValue,
        valueAdapter = StringAsIntSharedPrefsValueAdapter
    )

    protected fun stringAsLongValue(
        key: String,
        defValue: Long = 0
    ): RxPreference<Long> = customValue(
        key = key,
        defValue = defValue,
        valueAdapter = StringAsLongSharedPrefsValueAdapter
    )

    protected fun stringAsFloatValue(
        key: String,
        defValue: Float = 0f
    ): RxPreference<Float> = customValue(
        key = key,
        defValue = defValue,
        valueAdapter = StringAsFloatSharedPrefsAdapter
    )

    protected fun intValue(
        key: String,
        defValue: Int = 0
    ): RxPreference<Int> = customValue(
        key = key,
        defValue = defValue,
        valueAdapter = IntSharedPrefsValueAdapter
    )

    protected fun longValue(
        key: String,
        defValue: Long = 0
    ): RxPreference<Long> = customValue(
        key = key,
        defValue = defValue,
        valueAdapter = LongSharedPrefsValueAdapter
    )

    protected fun floatValue(
        key: String,
        defValue: Float = 0f
    ): RxPreference<Float> = customValue(
        key = key,
        defValue = defValue,
        valueAdapter = FloatSharedPrefsValueAdapter
    )

    protected fun doubleValue(
        key: String,
        defValue: Double = 0.0
    ): RxPreference<Double> = customValue(
        key = key,
        defValue = defValue,
        valueAdapter = DoubleSharedPrefsValueAdapter
    )

    protected fun booleanValue(
        key: String,
        defValue: Boolean = false
    ): RxPreference<Boolean> = customValue(
        key = key,
        defValue = defValue,
        valueAdapter = BooleanSharedPrefsValueAdapter
    )

}
