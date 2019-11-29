package com.example.whattowatch.fragments;

import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.whattowatch.R;

public class PrefsFragment extends PreferenceFragmentCompat {

    public static final String SPLASH_PREF = "splash_scr";
    public static final String TOAST = "toast_obavestenje";
    public static final String NOTIFIKACIJA = "notifikacija_obavestenje";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.prefs);
        findPreference(TOAST).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return onChange(preference, newValue);
            }
        });

        findPreference(NOTIFIKACIJA).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return onChange(preference, newValue);
            }
        });
    }

    public boolean onChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        if (key.equals(TOAST)) {
            //Reset other items
            CheckBoxPreference p = (CheckBoxPreference)findPreference(NOTIFIKACIJA);
            p.setChecked(false);
        }
        else if (key.equals(NOTIFIKACIJA)) {
            //Reset other items
            CheckBoxPreference p = (CheckBoxPreference)findPreference(TOAST);
            p.setChecked(false);
        }

        return (Boolean)newValue;
    }


}
