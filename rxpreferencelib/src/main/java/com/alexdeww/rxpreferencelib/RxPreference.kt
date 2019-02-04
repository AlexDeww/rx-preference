package com.alexdeww.rxpreferencelib

import io.reactivex.Observable

//no framework dependencies
interface RxPreference<T> {
    val key: String
    var value: T
    val observable: Observable<T>
}