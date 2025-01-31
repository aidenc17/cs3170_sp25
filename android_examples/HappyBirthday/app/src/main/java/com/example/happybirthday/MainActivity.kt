package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
                    GreetingImage(
                        message = stringResource(R.string.happy_birthday_message),
                        from = stringResource(R.string.from),
                        modifier = Modifier
                            .padding(innerPadding)
                    )
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
    Column(
        verticalArrangement = Arrangement.Top, // things will be centered within the Column
                                                    // this does not specify where the Column goes in relation to its parent
        modifier = modifier.padding(8.dp))
    {// stuff in here will be drawn in the column
        // text to display the happy birthday message
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp, // without line height, the lines are jumbled together
            textAlign = TextAlign.Center // textAlign aligns the text within its box
        )
        // text to display the from String
        Text(
            text = from,
            fontSize = 36.sp,
            //textAlign = TextAlign.Right,
            modifier = Modifier
                .border(width = 1.dp, color = Color.Blue)
                .padding(16.dp)
                .align(alignment = Alignment.End) // this align moves the text box to the End (Right)
        )
    }
}

@Composable
fun GreetingImage(
    message: String,
    from: String,
    modifier: Modifier = Modifier,
) {
    // R gives us access to resources
    // painter resource takes the ID of a drawable
    val image = painterResource(R.drawable.img_0035)

    Box(modifier = modifier)
    { // Box lets us place items on top of each other, and we can control how they are arranged
        Image(
            painter = image,
            // contentDescription is required for images for accessibility purposes
            // for assignments you can pass null, or give a string if you wish
            contentDescription = "A dog",
            // this site has some info on content scale
            // https://developer.android.com/develop/ui/compose/graphics/images/customize
            contentScale = ContentScale.Crop,
            // alpha is how transparent, 1 = non-transparent 0 = fully transparent
            alpha = 0.5f
        )

        GreetingText(
            message = message,
            from = from,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingImage(
            // string resources are in res/values/strings.xml
            message = stringResource(R.string.happy_birthday_message),
            from = stringResource(R.string.from),
        )
    }
}
