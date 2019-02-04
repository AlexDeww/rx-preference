package com.alexdeww.rxpreferencelib.adapters

import android.content.SharedPreferences
import com.alexdeww.rxpreferencelib.rxsharedprefs.RxSharedPreferencesAdapter

class DoubleSharedPrefsAdapter(
        sharedPreferences: SharedPreferences
) : RxSharedPreferencesAdapter<Double>(sharedPreferences) {
    override fun getValue(key: String, defValue: Double): Double =
            java.lang.Double.longBitsToDouble(sharedPreferences.getLong(key, java.lang.Double.doubleToRawLongBits(defValue)))

    override fun setValue(key: String, newValue: Double) =
            sharedPreferences.edit().apply { putLong(key, java.lang.Double.doubleToRawLongBits(newValue)) }.apply()
}