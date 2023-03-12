package com.snacks.onegai.codegpt.ui.settings.internal

import com.intellij.openapi.options.Configurable
import com.snacks.onegai.codegpt.ui.settings.SettingsPanel
import javax.swing.JComponent

class SettingsConfigurable : Configurable {
    private var settingsPanel: SettingsPanel
    private var settingsState: SettingsState = SettingsState.getInstance()

    init {
        // Set the values of the text fields from the saved settings
        settingsPanel = SettingsPanel(settingsState.url, settingsState.apiKey)
    }

    override fun createComponent(): JComponent {
        return settingsPanel.getMainPanel()
    }

    override fun isModified(): Boolean {
        return settingsPanel.getApiKey() != settingsState.apiKey || settingsPanel.getUrl() != settingsState.url
    }

    override fun apply() {
        // Save the values of the text fields to the settings
        val settingsState = SettingsState.getInstance()
        settingsState.url = settingsPanel.getUrl()
        settingsState.apiKey = settingsPanel.getApiKey()
    }

    override fun getDisplayName(): String {
        return "Onegai CodeGPT: Settings"
    }
}