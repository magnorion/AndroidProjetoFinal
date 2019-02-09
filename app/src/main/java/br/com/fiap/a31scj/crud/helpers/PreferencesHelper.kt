package br.com.cardote.fichadetreino.helpers

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context) {

    companion object {
        private const val STAY = "STAY"
        private const val USER = "USER"
    }
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)


    var stayConnected = preferences.getBoolean(STAY, false)
        set(value) = preferences.edit().putBoolean(STAY, value).apply()

    var user = preferences.getString(USER, "")
        set(value) = preferences.edit().putString(USER, value).apply()
}
