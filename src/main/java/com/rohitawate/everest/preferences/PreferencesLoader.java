/*
 * Copyright 2018 Rohit Awate.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rohitawate.everest.preferences;

import com.rohitawate.everest.Main;
import com.rohitawate.everest.logging.LoggingService;
import com.rohitawate.everest.misc.EverestUtilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Loads up custom values into Preferences from Everest/config/preferences.json.
 */
public class PreferencesLoader {
    private static final File PREFS_FILE = new File("Everest/config/preferences.json");

    public Preferences loadPrefs() {
        new EverestUtilities();
        Preferences preferences = null;

        if (!PREFS_FILE.exists()) {
            LoggingService.logInfo("Preferences file not found. Everest will use the default values.", LocalDateTime.now());
            return new Preferences();
        }

        try {
            preferences = EverestUtilities.jsonMapper.readValue(PREFS_FILE, Preferences.class);
            LoggingService.logInfo("Preferences loaded.", LocalDateTime.now());
        } catch (IOException e) {
            LoggingService.logInfo("Could not parse preferences file. Everest will use the default values.", LocalDateTime.now());
            preferences = new Preferences();
        }

        return preferences;
    }

    public void savePrefs() {
        try {
            EverestUtilities.jsonMapper.writeValue(PREFS_FILE, Main.preferences);
            LoggingService.logInfo("Application settings saved.", LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}