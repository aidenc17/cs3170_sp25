# Project 03 - Character Creator Remastered

## Overview
For Project 03, we're going to extend the work we did on the Character Creator for Project 02. If you think modifying your Project 02 seems like too much work, or you would just rather, you can use my version of Project 02 as a starting point. 

After Project 03, for Project 04 we are going to integrate the Character Card from Project 01 and use the functionality of the Character Creator to populate the values for the Character Card. You may want to keep this fact in mind as well.

Before we can integrate our Character Card into the Character creator, we want to do some refactoring to make our job easier. First, we're going to add a ViewModel to separate the state from the UI. Then, we're going to have the ability to select pre-built characters.

## ViewModel
1. Prepare your project by adding the following to `build.gradle.kts (Module :app)`
```   
dependencies {
// other dependencies

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
//...
}
```
2. Create a Kotlin class/file called `CharacterCreatorViewModel` and extend it from the ViewModel class
```
import androidx.lifecycle.ViewModel

class CharacterCreatorViewModel : ViewModel() {
}
```

3. Add a data class for UI state called `CharacterCreatorUiState`.
```
data class CharacterCreatorUiState(
   // state variables such as character stats, name, etc. go here
   // give them default values so you don't have to specify when 
   // you first create the state
)
```

4. Add `_uiState` to the `CharacterCreatorViewModel` class
```
import kotlinx.coroutines.flow.MutableStateFlow

// UI state
private val _uiState = MutableStateFlow(CharacterCreatorUiState())
```

4. Add `uiState` to serve as the public, read-only property for the UI to use
```
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// UI state
private val _uiState = MutableStateFlow(CharacterCreatorUiState())
val uiState: StateFlow<CharacterCreatorUiState> = _uiState.asStateFlow()
```

## CharacterCreatorUiState
As for refactoring your program to move state into the ViewModel, you can put all the individual variables into the `CharacterCreatorUiState` class. However, there are other approaches that may make things easier. Instead of making variables for each stat, you could use something like an array or map to store them all in the same place. So you could have an array like 
```
val statValues = arrayOf(0, 0, 0, 0)
```
Where `statValues[0]` is stamina, `statValues[1]` is strength, etc. 

Another approach would use a map. This might look like 
```
var statMap = HashMap<String, Int>()
```
Then you could get stats with something like `statMap["stamina"]`, `statMap["strength"]`, etc. Another useful data structure could be an array of the stats like
```
val statStrings = arrayOf(
    "stamina",
    "strength",
    "agility",
    "intellect"
)
```
You could use this array to initialize your map. For example, you could loop through the stat strings to add all the stats to the map like:
```
for (statString in statStrings) {
    statMap[statString] = 0
}
```
This would initialize all the stats to zero. A good place for this would be in the `init` section of the ViewModel. You can a `private` method for `CharacterCreatorViewModel` called `resetCharacter()` that set the `_uiState.value` to a default `CharacterCreatorUiState`, then fill the `statMap`. The `init` would just be 
```
init {
    resetCharacter()
}
```

## DataSource 
After refactoring and adding the ViewModel, we want to have our app be able to choose from default characters like `Warrior`, `Paladin`, `Rogue`, `Mage`, etc. Each of these would have a different set of stats. Also, after loading the character, we could also still use talent points to increase certain stats. 

Create a new Kotlin class/file called `DataSource`. In this class, we can put the base stats for each character. In this class, put the following things:

1. `val charStrings = arrayOf("Warrior", "Rogue", "Paladin", "Mage")`

    If you already have this somewhere in your project, you can move it to `DataSource`

2. Also if you have the `statStrings` array, you can move it to `DataSource` also. If not, add the following:
```
val statStrings = arrayOf(
    "stamina",
    "strength",
    "agility",
    "intellect"
)
```

3. To give our characters base stats, we can make a 2-dimensional array. Then, `baseStats[0]` would be a list of stats such that `baseStats[0][0]` would be the `Warrior` `stamina` stat, and `baseStats[3][3]` would be the `Mage` `intellect` stat.
```
val baseStats = arrayOf(
    arrayOf(19, 18, 8, 5),
    arrayOf(6, 11, 22, 11),
    arrayOf(14, 14, 10, 12),
    arrayOf(8, 7, 13, 22)
)
```

4. We can use these arrays on their own, or we can again create a `Map` to store all the base stats. Add the functino `loadCharacters()` to `DataSource`
```   
fun loadCharacters(): Map<String, Map<String, Int>> {
    var result = HashMap<String,  Map<String, Int>>()
    for (charNameStats in charStrings.zip(baseStats)) {
        var statMap = HashMap<String, Int>()
        for (stats in statStrings.zip(charNameStats.second)) {
            statMap += stats
        }
        result[charNameStats.first] = statMap
    }
    return result
}
```

Using these additions, you can now add an `ExposedDropDownMenuBox` to select the character.

## Base Stat Mins 
Since the pre-made characters are given a certain set of stats, it would only make sense that the starting stats are the minimum for each stat. So, when decreasing a stat, you should ensure that doing so won't make it drop below the base stat for that character.

## Turn-in and Grading
Zip your entire Android studio project folder and upload it to the corresponding Dropbox in Pilot for this assignment. This project is worth 25 points, distributed as follows:

| Task                                                            | Points |
|-----------------------------------------------------------------|-|
| App has separation of tasks with state handled by a ViewModel   | 5 |
| Stats are mutated by the increment/decrement buttons            | 5 |
| Outputs character values/points remaining similar to Project01  | 5 |
| Can select a premade character                                  | 5 |
| Stats stay above base level for each character       | 5 | 

