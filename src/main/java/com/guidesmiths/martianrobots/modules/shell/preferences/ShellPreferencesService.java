package com.guidesmiths.martianrobots.modules.shell.preferences;

public interface ShellPreferencesService {
    String getPreference(String preferenceKey);
    void setPreference(String preferenceKey, String preferenceValue);
}
