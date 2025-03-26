package com.example.charactercreator


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charactercreator.ui.theme.CharacterCreatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharacterCreatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharacterCreatorApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterCreatorApp(modifier: Modifier = Modifier) {
    //val useMap = true
    val MAX_STATS = 25
    //var (statArray, setStatArray) = remember { mutableStateOf(Array<Int>(size = 4, init = { 0 })) }
    var (statMap, setStatMap) = remember {
        (mutableStateOf(
            mutableMapOf(
                "Stamina" to 0,
                "Strength" to 0,
                "Agility" to 0,
                "Intellect" to 0,
            )
        ))
    }
    //var statList = mutableListOf(0, 0, 0, 0)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "Character Creator", fontSize = 36.sp,
            modifier = Modifier.padding(bottom = 64.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(statMap.keys.toList())
                { stat ->
                    StatButtons(
                        statName = stat,
                        value = statMap[stat].toString(),
                        onPlusClick = {
                            if (statMap.values.sum() < MAX_STATS) {
                                setStatMap((statMap + (stat to statMap[stat]?.plus(1))) as HashMap<String, Int>)
                            }
                        },
                        onMinusClick = {
                            if (statMap.getOrDefault(stat, 0) > 0) {
                                setStatMap((statMap + (stat to statMap[stat]?.minus(1))) as HashMap<String, Int>)
                            }
                        },
                    )
                }
            }
        }

        Spacer(Modifier.padding(vertical = 8.dp))
        Text(text = "Points remaining: ${MAX_STATS - statMap.values.sum()}/${MAX_STATS}")
        Spacer(Modifier.padding(vertical = 8.dp))
        AttributeList(stats = statMap)
    }
}

@Composable
fun AttributeList(
    stats: Map<String, Int>,
    modifier: Modifier = Modifier
) {
    val attributeMap = getAttributesFromStats(stats)
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 64.dp)
    ) {
        items(attributeMap.keys.toList()) { attribute ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "${attribute}:")
                Text(text = "${attributeMap.getOrDefault(attribute, 0)}")
            }
        }
    }
}

private fun getAttributesFromStats(stats: Map<String, Int>): Map<String, Int> {
    return mapOf(
        "Hit points" to
            2 * stats.getOrDefault("Stamina", 0) +
                    stats.getOrDefault("Strength", 0),
        "Health regeneration" to
            2 * stats.getOrDefault("Stamina", 0) +
                    (stats.getOrDefault("Strength", 0) +
                            stats.getOrDefault("Agility", 0)),
        "Physical damage" to
                2 * stats.getOrDefault("Strength", 0) +
                    stats.getOrDefault("Agility", 0),
        "Magic damage" to
            2 * stats.getOrDefault("Agility", 0) +
                    stats.getOrDefault("Intellect", 0)
    )
}

@Composable
fun StatButtons(
    statName: String,
    value: String,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        // increment stat
        Button(
            onClick = onPlusClick,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24),
                contentDescription = ""
            )
        }
        Text(
            text = statName
        )

        Text(
            text = value
        )
        Button(onClick = onMinusClick) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterCreatorPreview() {
    CharacterCreatorTheme {
        CharacterCreatorApp(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}