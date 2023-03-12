package com.snacks.onegai.codegpt.plugin.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.ui.components.JBLabel
import com.snacks.onegai.codegpt.api.ChatGPTClient
import javax.swing.SwingConstants

class WriteTestsAction(private val chatGPTClient: ChatGPTClient) : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        e.getData(LangDataKeys.EDITOR)?.let { editor ->
            editor.selectionModel.selectedText?.let { selectedCodes ->
                showResponseInPopup(chatGPTClient.ask(selectedCodes), editor.contentComponent)
            }
        }
    }

    private fun showResponseInPopup(response: String, parentComponent: java.awt.Component) {
        val label = JBLabel(response, SwingConstants.CENTER)
        val factory = JBPopupFactory.getInstance()
        val popup = factory.createComponentPopupBuilder(label, label)
            .setMovable(true)
            .setResizable(true)
            .setRequestFocus(true)
            .setCancelOnClickOutside(true)
            .createPopup()
        popup.showInCenterOf(parentComponent)
    }
}