package com.alexdeww.rxpreferencelib

import io.reactivex.rxjava3.core.Observable

//no framework dependencies
interface RxPreference<T> {
    val key: String
    var value: T
    val observable: Observable<T>
}