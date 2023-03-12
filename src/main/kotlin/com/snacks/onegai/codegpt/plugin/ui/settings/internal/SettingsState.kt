package com.snacks.onegai.codegpt.plugin.ui.settings.internal

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "com.snacks.onegai.codegpt.plugin.ui.settings.internal.SettingsState", storages = [Storage("OnegaiCodeGPTSettings.xml")])
class SettingsState : PersistentStateComponent<SettingsState> {
    var url: String = ""
    var apiKey: String = ""

    override fun getState(): SettingsState {
        return this
    }

    override fun loadState(state: SettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        fun getInstance(): SettingsState {
            return ApplicationManager.getApplication().getService(SettingsState::class.java)
        }
    }
}