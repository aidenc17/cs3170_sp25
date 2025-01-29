package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                // you might have red squiggles under innerPadding, it shouldn't be an issue
                }
            }
        }
    }
}

/**
 * GreetingText displays a message and a from String in a column
 */
@Composable
fun GreetingText(
    message: String,
    from: String,
    modifier: Modifier = Modifier
) {
    // Column places elements vertically
    // the things we want to be in the column go in between
    // the curly braces right after the parentheses
    // since Column is the parent element, we pass the modifier on to it
    Column(modifier = modifier)
    {// stuff in here will be drawn in the column
        // text to display the happy birthday message
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp, // without line height, the lines are jumbled together
        )
        // text to display the from String
        Text(
            text = from,
            fontSize = 36.sp
        )
    }

}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingText(
            message = "Happy Birthday James!",
            from = "From Paris"
        )
    }
}
