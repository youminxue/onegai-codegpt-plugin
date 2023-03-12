package com.snacks.onegai.codegpt.ui.settings

import com.intellij.ui.layout.panel
import javax.swing.JPanel
import javax.swing.JTextField

/**
 * The settings panel for the plugin.
 */
class SettingsPanel(url: String, apiKey: String) {
    private val urlTextField: JTextField
    private val apiKeyField: JTextField

    init {
        urlTextField = JTextField(url)
        apiKeyField = JTextField(apiKey)
    }

    fun getMainPanel(): JPanel {
        return panel {
            titledRow("GhatGPT Settings") {
            }
            row("URL:") {
                urlTextField().comment("The URL of the OpenAI API endpoint.")
            }
            row("API key:") {
                apiKeyField().comment("You can find your Secret API key in your <a href=\"https://platform.openai.com/account/api-keys\">User settings</a>.")
            }
        }
    }

    fun getUrl(): String {
        return urlTextField.text
    }

    fun getApiKey(): String {
        return apiKeyField.text
    }
}