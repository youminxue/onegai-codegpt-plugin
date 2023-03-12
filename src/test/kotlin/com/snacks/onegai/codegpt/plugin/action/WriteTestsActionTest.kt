package com.snacks.onegai.codegpt.plugin.action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.SelectionModel
import com.intellij.openapi.ui.popup.ComponentPopupBuilder
import com.intellij.openapi.ui.popup.JBPopup
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.snacks.onegai.codegpt.api.ChatGPTClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Answers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class WriteTestsActionTest {
    @InjectMocks
    lateinit var writeTestsAction: WriteTestsAction

    @Mock
    private lateinit var chatGPTClient: ChatGPTClient

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var jbPopupFactory: JBPopupFactory

    @Test
    fun `should return unit test codes for selected code`() {
        // given:
        val selectedCode = "some-selected-code"
        val mockSelectionModel = mock<SelectionModel> {
            on {
                selectedText
            } doReturn selectedCode
        }
        val editor = mock<Editor> {
            on {
                selectionModel
            } doReturn mockSelectionModel
            on { contentComponent } doReturn mock()
        }

        val event = mock<AnActionEvent> {
            on {
                getData(LangDataKeys.EDITOR)
            } doReturn editor
        }
        whenever(chatGPTClient.ask(selectedCode)).doReturn("some-unit-test-codes")

        // when:
        mockStatic(JBPopupFactory::class.java).use {
            it.`when`<Any>(JBPopupFactory::getInstance).thenReturn(jbPopupFactory)
            val jbPopup = mock<JBPopup>()
            val componentPopupBuilder = mock<ComponentPopupBuilder>(defaultAnswer = Answers.RETURNS_SELF) {
                on {
                    createPopup()
                } doReturn jbPopup
            }
            whenever(jbPopupFactory.createComponentPopupBuilder(any(), any())).thenReturn(componentPopupBuilder)

            // when:
            writeTestsAction.actionPerformed(event)

            // then:
            verify(chatGPTClient).ask(selectedCode)
            verify(jbPopupFactory).createComponentPopupBuilder(any(), any())
            verify(componentPopupBuilder).setMovable(true)
            verify(componentPopupBuilder).setResizable(true)
            verify(componentPopupBuilder).setRequestFocus(true)
            verify(componentPopupBuilder).setCancelOnClickOutside(true)
            verify(componentPopupBuilder).createPopup()
            verify(jbPopup).showInCenterOf(editor.contentComponent)
        }
    }
}
