package com.helicoptera.onlyjojofans.ui.settings

import android.app.UiModeManager
import android.content.Context
import android.os.Bundle
import android.os.LocaleList
import com.helicoptera.onlyjojofans.R
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.preference.*
import com.helicoptera.onlyjojofans.ApplicationSettings
import java.lang.IllegalArgumentException
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val languagePreference = preferenceScreen.findPreference<Preference>("language")
        languagePreference?.summary = getLanguageTitle()
        languagePreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener() { preference, language ->
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
                    resources.updateConfiguration(configuration, displayMetrics)
                    activity?.recreate()
                }

                return@OnPreferenceChangeListener true
            }

        val darkModePreference = preferenceScreen.findPreference<SwitchPreferenceCompat>("darkMode")
        darkModePreference?.isChecked = AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES
        darkModePreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener() { preference, enabled ->
                val enabled = enabled as? Boolean
                enabled?.let {
                    if (enabled) {
                        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                    } else {
                        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                    }
                    activity?.recreate()
                }

                return@OnPreferenceChangeListener true
            }

        val fontPreference = preferenceScreen.findPreference<Preference>("size")
        fontPreference?.summary = (ApplicationSettings.fontSize).toString()
        fontPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener() { preference, value ->
                val value = value as? Int
                value?.let {
                    preference.summary = value.toString()
                    ApplicationSettings.fontSize = value + 8
                    activity?.recreate()
                }

                return@OnPreferenceChangeListener true
            }

        val fontFamilyPreference = preferenceScreen.findPreference<Preference>("family")
        fontFamilyPreference?.summary = ApplicationSettings.fontName
        fontFamilyPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener() { preference, family ->
                val family = family as? String
                family?.let { family ->
                    preference.summary = family
                    ApplicationSettings.fontName = family
                    activity?.recreate()
                }

                return@OnPreferenceChangeListener true
            }

    }

    private fun getLanguageTitle() : String {
        val language = resources.configuration.locales[0].language
        return when (language) {
            "en" -> resources.getString(R.string.english)
            "ru" -> resources.getString(R.string.russian)
            else -> throw IllegalArgumentException()
        }
    }


}