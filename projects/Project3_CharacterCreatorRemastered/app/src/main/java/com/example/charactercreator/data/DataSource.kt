package com.example.charactercreator.data

val charStrings = arrayOf("Warrior", "Rogue", "Paladin", "Mage")

val statStrings = arrayOf(
    "stamina",
    "strength",
    "agility",
    "intellect"
)

val baseStats = arrayOf(
    arrayOf(5, 3, 8, 1),
    arrayOf(6, 2, 2, 1),
    arrayOf(4, 1, 0, 6),
    arrayOf(8, 1, 1, 2)
)

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
