package com.alexdeww.rxpreferencelib

import io.reactivex.rxjava3.core.Flowable

interface RxPreference<T : Any> {
    val key: String
    var value: T
    val valueFlowable: Flowable<T>
}
