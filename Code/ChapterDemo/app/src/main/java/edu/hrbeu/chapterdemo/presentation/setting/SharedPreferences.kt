package edu.hrbeu.chapterdemo.presentation.setting

import android.content.Context
import android.content.Context.MODE_PRIVATE

const val PREFERENCE_NAME = "SaveSetting"
const val MODE = MODE_PRIVATE


fun loadSharedPreferences(context: Context): SharedPreferencesData {
    println("loadSharedPreferences()")
    val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, MODE)
    return SharedPreferencesData(
        isAutoSave = sharedPreferences.getBoolean("IsAutoSave", true) ,
        isRemoveDuplicate = sharedPreferences.getBoolean("IsRemoveDuplicate", true) ,
    )
}

fun saveSharedPreferences(context: Context, isAutoSave: Boolean, isRemoveDuplicate: Boolean) {
    println("saveSharedPreferences(), isAutoSave:$isAutoSave, isRemoveDuplicate:$isRemoveDuplicate")
    val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, MODE)
    with(sharedPreferences.edit()) {
        putBoolean("IsAutoSave", isAutoSave)
        putBoolean("IsRemoveDuplicate", isRemoveDuplicate)
        apply()
    }
}

data class SharedPreferencesData(
    val isAutoSave: Boolean,
    val isRemoveDuplicate: Boolean
)


