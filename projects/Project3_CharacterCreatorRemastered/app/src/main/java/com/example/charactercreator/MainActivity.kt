package com.example.charactercreator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charactercreator.ui.theme.CharacterCreatorTheme
import com.example.charactercreator.data.loadCharacters
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCreatorApp(modifier: Modifier = Modifier, viewModel: CharacterCreatorViewModel = viewModel()) {
    //collect stats from viewmodel
    val uiState by viewModel.uiState.collectAsState()
    val stats = uiState.stats
    val baseStats = viewModel.baseStats.value
    val maxStats = 25

    // load in preset characters
    val characters = loadCharacters()
    var selectedCharacter by remember { mutableStateOf("Warrior") }
    var expanded by remember { mutableStateOf(false) }

    // Set base stats for the selected character
    LaunchedEffect(selectedCharacter) {
        viewModel.setBaseStats(characters[selectedCharacter] ?: mapOf())
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "Character Creator", fontSize = 36.sp,
            modifier = Modifier.padding(bottom = 64.dp)
        )

        // new dropdown section
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            TextField(
                value = selectedCharacter,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Character") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                loadCharacters().keys.forEach { char ->
                    DropdownMenuItem(
                        text = { Text(char) },
                        onClick = {
                            selectedCharacter = char
                            //base stats for the premade characters (changed to be my own)
                            viewModel.setBaseStats(characters[char] ?: mapOf()) // elvis operator!
                            expanded = false
                        }
                    )
                }
            }
        }

        //stats control
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
                items(stats.keys.toList()) { stat ->
                    StatButtons(
                        statName = stat,
                        value = stats[stat].toString(),
                        onPlusClick = {
                            println("Attempting to increase $stat")
                            if (stats.values.sum() < maxStats) {
                                val newStatValue = stats[stat]?.plus(1) ?: 0
                                viewModel.updateStat(stat, newStatValue)
                                println("New value for $stat: $newStatValue")
                            }
                        },
                        onMinusClick = {
                            println("Attempting to decrease $stat")
                            val baseStat = baseStats[stat] ?: 0
                            if (stats.getOrDefault(stat, 0) > baseStat) {
                                val newStatValue = stats[stat]?.minus(1) ?: 0
                                viewModel.updateStat(stat, newStatValue)
                                println("New value for $stat: $newStatValue")
                            }
                        },
                    )
                }
            }
        }

        Spacer(Modifier.padding(vertical = 8.dp))
        Text(text = "Points remaining: ${maxStats - stats.values.sum()}/${maxStats}")
        Spacer(Modifier.padding(vertical = 8.dp))
        AttributeList(stats = stats)
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
                2 * stats.getOrDefault("stamina", 0) +
                stats.getOrDefault("strength", 0),
        "Health regeneration" to
                2 * stats.getOrDefault("stamina", 0) +
                (stats.getOrDefault("strength", 0) +
                        stats.getOrDefault("agility", 0)),
        "Physical damage" to
                2 * stats.getOrDefault("strength", 0) +
                stats.getOrDefault("agility", 0),
        "Magic damage" to
                2 * stats.getOrDefault("agility", 0) +
                stats.getOrDefault("intellect", 0)
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
    ) {
        Button(
            onClick = {

                onPlusClick()
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24),
                contentDescription = "Increase $statName"
            )
        }

        Text(text = statName)
        Text(text = value)

        Button(onClick = {

            onMinusClick()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                contentDescription = "Decrease $statName"
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