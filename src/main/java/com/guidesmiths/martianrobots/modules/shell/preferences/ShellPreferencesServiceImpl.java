package com.guidesmiths.martianrobots.modules.shell.preferences;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.guidesmiths.martianrobots.util.constraints.Constraints.PREFERENCE_INTERACTIVE_OUTPUT_DEFAULT_VALUE;

@Service
public class ShellPreferencesServiceImpl implements ShellPreferencesService {
    @Autowired
    private ShellPreferencesRepositoryImpl shellPreferencesRepositoryImpl;

    private final Logger LOG = Logger.getLogger(ShellPreferencesServiceImpl.class);

    public String getPreference(String preferenceKey){
        try {
            return shellPreferencesRepositoryImpl.getPreference(preferenceKey);
        }catch (Exception e){
            LOG.debug("Preference " + preferenceKey + " not set yet");
            return PREFERENCE_INTERACTIVE_OUTPUT_DEFAULT_VALUE;
        }
    }
    public void setPreference(String preferenceKey, String preferenceValue){
        shellPreferencesRepositoryImpl.setPreference(preferenceKey, preferenceValue);
    }
}
