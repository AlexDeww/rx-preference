package com.alexdeww.rxpreferencelib

import io.reactivex.Observable

interface Preference<T> {
    val key: String
    var value: T

    fun asObservable(): Observable<T>
}