package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent() {
            TipTimeTheme {
                TipCalculatorApp()
            }
        }

        composeTestRule
            .onNodeWithText("Bill Amount")
            .assertExists("Could not not find Bill Amount")
            .performTextInput("10")
        composeTestRule
            .onNodeWithText("Tip Percentage")
            .assertExists("Could not find Tip Percentage")
            .performTextReplacement("20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule
            .onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("Could not find Tip Amount")
    }

}