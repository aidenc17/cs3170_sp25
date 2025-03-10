package com.example.charactercard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.icons.twotone.ThumbUp
import androidx.compose.material.icons.twotone.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charactercard.ui.theme.CharacterCardTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharacterCardTheme {
                Scaffold() { paddingValues ->
                    CharacterCardApp(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun CharacterCardApp(modifier: Modifier = Modifier) {
    CardImage(
        cardTitle = stringResource(R.string.cardtitle),
        castingCost = integerResource(id = R.integer.castingCost),
        borderColorId = R.color.borderColor,
        cardImageId = R.drawable.drinky,
        cardType = stringResource(R.string.cardType),
        cardText = stringResource(R.string.cardtext),
        powerStat = integerResource(id = R.integer.powerStat),
        toughnessStat = integerResource(id = R.integer.toughnessStat),
        modifier = modifier
    )
}

@Composable
fun TitleCost(
    title: String,
    cost: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.white))
            .border(BorderStroke(1.dp, Color.Black))

    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(start = 8.dp)
        )
        Row(
            modifier = Modifier
                .padding(end = 8.dp)
        ) {
            Text(
                text = "$cost"
            )
            Icon(
                painter = painterResource(R.drawable.baseline_offline_bolt_24),
                contentDescription = "",
                tint = colorResource(R.color.my_yellow)
                //Icons.TwoTone.Warning, ""
            )
        }

    }
}

@Composable
fun CardType(
    type: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(colorResource(id = R.color.white))
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black))
    ) {
        Text(
            text = type,
            modifier = Modifier.padding(start = 8.dp)
        )
        Icon(
            painter = painterResource(R.drawable.baseline_nightlight_round_24),
            contentDescription = "",
            tint = Color.Blue,
            modifier = Modifier.padding(end = 8.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun CardTextStats(
    cardText: String,
    power: Int,
    toughness: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(id =  R.color.white))
            .border(BorderStroke(1.dp, Color.Black))
    ) {
        Text (
            text = cardText,
            modifier = Modifier
                .padding(start =  8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
        Text(
            text = "$power/$toughness",
            //textAlign = TextAlign.End
        ) }
    }
}



@Composable
fun CardImage(
    cardTitle: String,
    castingCost: Int,
    borderColorId: Int,
    cardImageId: Int,
    cardType: String,
    cardText: String,
    powerStat: Int,
    toughnessStat: Int,
    modifier: Modifier = Modifier
    ) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(colorResource(id = borderColorId))
            .fillMaxHeight()
    ) {
        TitleCost(
            title = cardTitle,
            cost = castingCost,
            modifier = Modifier
                .padding(8.dp)
        )

        Image(
            painter = painterResource(id = cardImageId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .border(BorderStroke(1.dp, Color.Black))
        )

        CardType(
            type = cardType,
            modifier = Modifier
                .padding(8.dp)
        )

        CardTextStats(
            cardText = cardText,
            power = powerStat,
            toughness = toughnessStat,
            modifier = Modifier
                .padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CharacterCardTheme {
        CardImage(
            cardTitle = stringResource(R.string.cardtitle),
            castingCost = integerResource(id = R.integer.castingCost),
            borderColorId = R.color.borderColor,
            cardImageId = R.drawable.drinky,
            cardType = stringResource(R.string.cardType),
            cardText = stringResource(R.string.cardtext),
            powerStat = integerResource(id = R.integer.powerStat),
            toughnessStat = integerResource(id = R.integer.toughnessStat)
        )
            }
}