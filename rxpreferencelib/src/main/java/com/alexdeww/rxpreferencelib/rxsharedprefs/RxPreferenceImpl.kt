package com.alexdeww.rxpreferencelib.rxsharedprefs

import android.util.Log
import com.alexdeww.rxpreferencelib.RxPreference
import com.alexdeww.rxpreferencelib.common.RxPreferenceAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable

open class RxPreferenceImpl<T>(
    override val key: String,
    protected val defValue: T,
    protected val adapter: RxPreferenceAdapter<T>,
    prefKeyChangesObservable: Observable<String>
) : RxPreference<T> {

    protected open val _observable: Observable<T> = prefKeyChangesObservable
        .filter { key == it }
        .startWithItem("<init>") // Dummy value to trigger initial load.
        .map { value }

    override var value: T
        get() {
            return try {
                adapter.getValue(key, defValue)
            } catch (e: Exception) {
                Log.e("RxPreferenceImpl", "Can't get preference value($key)", e)
                defValue
            }
        }
        set(value) {
            adapter.setValue(key, value)
        }

    override val observable: Observable<T> get() = _observable.observeOn(AndroidSchedulers.mainThread())

}