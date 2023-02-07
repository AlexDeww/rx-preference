package com.alexdeww.rxpreferencelib.rxsharedprefs

import android.content.SharedPreferences
import android.util.Log
import com.alexdeww.rxpreferencelib.RxPreference
import com.alexdeww.rxpreferencelib.common.SharedPreferenceValueAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

internal class RxPreferenceImpl<T : Any>(
    override val key: String,
    private val defValue: T,
    private val adapter: SharedPreferenceValueAdapter<T>,
    private val sharedPreferences: SharedPreferences,
    prefKeyChangesObservable: Observable<String>
) : RxPreference<T> {

    private val _observable: Observable<T> by lazy {
        prefKeyChangesObservable
            .filter { key == it }
            .startWithItem("<init>") // Dummy value to trigger initial load.
            .map { value }
    }

    @Suppress("TooGenericExceptionCaught")
    override var value: T
        get() = try {
            adapter.getValue(sharedPreferences, key, defValue)
        } catch (e: Throwable) {
            Log.e("RxPreference", "Can't get preference value($key)", e)
            defValue
        }
        set(value) = adapter.setValue(sharedPreferences, key, value)

    override val valueFlowable: Flowable<T> by lazy {
        _observable
            .toFlowable(BackpressureStrategy.LATEST)
            .observeOn(AndroidSchedulers.mainThread())
    }

}
