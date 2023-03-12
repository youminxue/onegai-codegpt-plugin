package com.snacks.onegai.codegpt.ui.settings

import com.intellij.ui.TitledSeparator
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class SettingsPanelTest {
    @Test
    fun testGetMainPanel() {
        // Create an instance of the SettingsComponent class
        val url = "https://api.openai.com"
        val apiKey = "my-secret-api-key"
        val settingsPanel = SettingsPanel(url, apiKey)

        // Get the main panel and check that it has the expected components
        val mainPanel = settingsPanel.getMainPanel()

        // Check that the form panel has the expected components
        val components = mainPanel.components
        assertEquals(8, components.size)

        // Check that the first component is a titled separator with the expected text
        val titledSeparator = components[0] as TitledSeparator
        assertEquals("GhatGPT Settings", titledSeparator.text)

        // Check that the third component is a panel with the expected components
        val urlPanel = components[1] as JPanel

        val urlLabel = components[2] as JLabel
        assertEquals("URL:", urlLabel.text)

        val urlTextField = components[3] as JTextField
        assertEquals(url, urlTextField.text)

        val urlCommentLabel = components[4] as JLabel
        assertThat(urlCommentLabel.text, containsString("The URL of the OpenAI API endpoint."))

        // Check that the fifth component is a panel with the expected components
        val apiKeyLabel = components[5] as JLabel
        assertEquals("API key:", apiKeyLabel.text)

        val apiKeyTextField = components[6] as JTextField
        assertEquals(apiKey, apiKeyTextField.text)

        val apiCommentLabel = components[7] as JLabel
        assertThat(apiCommentLabel.text, containsString("You can find your Secret API key in your"))
    }

    @Test
    fun testGetUrl() {
        // Create an instance of the SettingsComponent class
        val url = "https://api.openai.com"
        val apiKey = "my-secret-api-key"
        val settingsPanel = SettingsPanel(url, apiKey)

        // Get the URL and check that it has the expected value
        val actualUrl = settingsPanel.getUrl()
        assertEquals(url, actualUrl)
    }

    @Test
    fun testGetApiKey() {
        // Create an instance of the SettingsComponent class
        val url = "https://api.openai.com"
        val apiKey = "my-secret-api-key"
        val settingsPanel = SettingsPanel(url, apiKey)

        // Get the API key and check that it has the expected value
        val actualApiKey = settingsPanel.getApiKey()
        assertEquals(apiKey, actualApiKey)
    }
}