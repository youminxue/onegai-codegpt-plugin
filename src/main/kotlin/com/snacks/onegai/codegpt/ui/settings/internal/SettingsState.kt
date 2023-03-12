package com.snacks.onegai.codegpt.ui.settings.internal

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "com.snacks.onegai.codegpt.ide.settings.SettingsState", storages = [Storage("OnegaiCodeGPTSettings.xml")])
class SettingsState : PersistentStateComponent<SettingsState.State> {
    var url: String = ""
    var apiKey: String = ""

    override fun getState(): State {
        val state = State()
        state.url = url
        state.apiKey = apiKey
        return state
    }

    override fun loadState(state: State) {
        url = state.url
        apiKey = state.apiKey
    }

    class State {
        var url: String = ""
        var apiKey: String = ""
    }

    companion object {
        fun getInstance(): SettingsState {
            return ApplicationManager.getApplication().getService(SettingsState::class.java)
        }
    }
}