package com.example.charactercreator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.KeyboardArrowDown
import androidx.compose.material.icons.twotone.KeyboardArrowUp
import androidx.compose.material.icons.twotone.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charactercreator.ui.theme.CharacterCreatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharacterCreatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharacterCreatorApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterCreatorApp(modifier: Modifier = Modifier) {
    var staminaValue by remember { mutableStateOf(0) }
    var strengthValue by remember { mutableStateOf(0) }

    Row() {
        StatButtons(
            statName = "Stamina",
            statValue = "$staminaValue",
            plusClick = { staminaValue++ },
            minusClick = { staminaValue-- }
        )
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        StatButtons(
            statName = "Strength",
            statValue = "$strengthValue",
            plusClick = { strengthValue++ },
            minusClick = { strengthValue-- }
        )
    }
}

@Composable
fun StatButtons(
    statName: String,
    statValue: String,
    plusClick: () -> Unit,
    minusClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // add to stat
        Button(
            onClick = plusClick
        ) {
            Icon(
                imageVector = Icons.TwoTone.KeyboardArrowUp,
                contentDescription = ""
            )
        }

        Text(text = statName)
        Text(text = statValue)

        // subtract from stat
        Button(
            onClick = minusClick
        ) {
            Icon(
                imageVector = Icons.TwoTone.KeyboardArrowDown,
                contentDescription = ""
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CharacterCreatorTheme {
        CharacterCreatorApp(modifier = Modifier.fillMaxSize())
    }
}