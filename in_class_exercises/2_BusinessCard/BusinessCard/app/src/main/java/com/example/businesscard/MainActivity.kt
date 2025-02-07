package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCardApp(modifier = Modifier.padding(innerPadding).fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun LogoNameTitle(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {
        Box (modifier = Modifier.padding(horizontal = 64.dp)){
            Image(
                painter = painterResource(id = R.drawable.android_logo),
                contentDescription = "Android Logo",
                //contentScale = ContentScale.Fit,
                modifier = Modifier
                    .background(color = colorResource(id = R.color.my_green))
                    .scale(0.75f)
                    //.padding(46.dp)
            )
        }
        Text(stringResource(R.string.my_name),
            fontSize = 50.sp,
            textAlign = TextAlign.Center)
        Text(stringResource(R.string.my_title),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            //modifier = Modifier.absoluteOffset(y=-20.dp)
        )
    }
}

@Composable
fun ContactInfo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row {
            Icon(
                painter = painterResource(R.drawable.baseline_smartphone_24),
                tint = Color.Cyan,
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(R.string.phone),
                color = Color.Cyan
            )
        }

        Row() {
            Icon(
                painter = painterResource(R.drawable.baseline_person_pin_24),
                tint = Color.Magenta,
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp)

            )
            Text(
                text = stringResource(R.string.social),
                color = Color.Magenta
            )
        }

        Row () {
            Icon(
                painter = painterResource(R.drawable.twotone_email_24),
                tint = Color.Yellow,
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp)

            )
            Text(
                text = stringResource(R.string.email),
                color = Color.Yellow
            )
        }
    }
}

@Composable
fun BusinessCardApp(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color = Color.DarkGray)
    ) {
        LogoNameTitle()
        Spacer(
            modifier = Modifier.padding(32.dp)
        )
        ContactInfo()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCardApp(
            modifier = Modifier.fillMaxSize()
        )
    }
}