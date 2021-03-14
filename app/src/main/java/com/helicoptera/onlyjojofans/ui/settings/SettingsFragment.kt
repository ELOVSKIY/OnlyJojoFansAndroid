package com.helicoptera.onlyjojofans.ui.settings

import android.app.UiModeManager
import android.content.Context
import android.os.Bundle
import android.os.LocaleList
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.helicoptera.onlyjojofans.R
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val languagePreference = preferenceScreen.findPreference<Preference>("language")
        languagePreference?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener() { preference, language ->
            val language = language as? String
            language?.let { language ->
                preference.summary = language

                var selectedLanguageCode = "en"
                when (language) {
                    resources.getString(R.string.english) -> selectedLanguageCode = "en"
                    resources.getString(R.string.russian) -> selectedLanguageCode = "ru"
                }
                val locale = Locale(selectedLanguageCode)
                Locale.setDefault(locale)
                val configuration = resources.configuration
                val displayMetrics = resources.displayMetrics
                val localeList = LocaleList(locale)
                configuration?.setLocales(localeList)
                context?.createConfigurationContext(configuration)
            }

            return@OnPreferenceChangeListener true
        }

        val darkModePreference = preferenceScreen.findPreference<Preference>("darkMode")
        darkModePreference?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener() { preference, enabled ->
            val enabled = enabled as? Boolean
            enabled?. let {
                if (enabled) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                }
            }

            return@OnPreferenceChangeListener true
        }
    }
}