package com.alexdeww.rxpreferencelib.rxsharedprefs

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.common.RxPreferenceAdapter

abstract class RxSharedPreferencesAdapter<T>(
        protected val sharedPreferences: SharedPreferences
) : RxPreferenceAdapter<T>