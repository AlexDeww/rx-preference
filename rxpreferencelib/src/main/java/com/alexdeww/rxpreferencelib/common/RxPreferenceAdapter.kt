package com.alexdeww.rxpreferencelib.common

interface RxPreferenceAdapter<T> {
    fun getValue(key: String, defValue: T): T
    fun setValue(key: String, newValue: T)
}
