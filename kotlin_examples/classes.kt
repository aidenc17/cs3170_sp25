data class Character(
    val name: String = "James",
    val statNames: Array<String> = arrayOf<String>("strength", "stamina", "agility", "intellect"),
	val statValues: Array<Int> = arrayOf<Int>(6, 8, 4, 9)
) {
    fun print() {
        println("$name, ${statNames[0]}: ${statValues[0]}")
    }
}

fun main() {
    val myCharacter = Character()
    myCharacter.print()
    println(myCharacter.name)
    println(myCharacter.statValues[0])
    
    val statNames = arrayOf<String>("strength", "stamina", "agility", "intellect")
	val statValues = arrayOf<Int>(6, 8, 4, 9)
    
    println(statNames.joinToString())
    statNames[2] = "muscles"
    for (name in statNames) {
        print("$name, ")
    }    
}