package com.alexdeww.rxpreferencelib

object StringAdapters {

    val STRING_TO_INT = LambdaPreferenceAdapter<Int>(
            { editor, key, v -> editor.putString(key, v.toString()) },
            { sharedPreferences, s, defVal -> sharedPreferences.getString(s, defVal.toString())?.toIntOrNull() ?: defVal }
    )

    val STRING_TO_LONG = LambdaPreferenceAdapter<Long>(
            { editor, key, v -> editor.putString(key, v.toString()) },
            { sharedPreferences, key, defVal -> sharedPreferences.getString(key, defVal.toString())?.toLongOrNull() ?: defVal }
    )

    val STRING_TO_DOUBLE = LambdaPreferenceAdapter<Double>(
            { editor, key, v -> editor.putString(key, v.toString()) },
            { sharedPreferences, key, defVal -> sharedPreferences.getString(key, defVal.toString())?.toDoubleOrNull() ?: defVal }
    )

    val STRING_TO_FLOAT = LambdaPreferenceAdapter<Float>(
            { editor, key, v -> editor.putString(key, v.toString()) },
            { sharedPreferences, key, defVal -> sharedPreferences.getString(key, defVal.toString())?.toFloatOrNull() ?: defVal }
    )

}