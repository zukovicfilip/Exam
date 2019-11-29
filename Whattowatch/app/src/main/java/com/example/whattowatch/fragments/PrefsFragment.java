package com.example.whattowatch.fragments;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import com.example.whattowatch.R;

public class PrefsFragment extends PreferenceFragmentCompat {

    public static final String SPLASH_PREF = "splash_scr";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.prefs);
    }
}
