package com.guidesmiths.martianrobots.modules.shell.preferences;

interface ShellPreferencesRepository {
    String getPreference(String preferenceKey);
    void setPreference(String preferenceKey, String preferenceValue);
}
