package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroes.data.Datasource
import com.example.superheroes.model.Hero
import com.example.superheroes.ui.theme.SuperHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperHeroesTheme {
                Surface() {
                    SuperHeroesApp()
                }
            }
        }
    }
}

@Composable
fun SuperHeroesApp() {

}

@Composable
fun HeroCard(
    hero: Hero,
    modifier: Modifier = Modifier
    ) {
    Card (
    ) {
        Row {
            Column() {
                Text(text = stringResource(id = hero.nameRes))
                Text(text = stringResource(id = hero.descriptionRes))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperHeroesTheme {
        //SuperHeroesApp()
        HeroCard(Datasource.loadHeroes()[0])
    }
}