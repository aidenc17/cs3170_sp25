// data class used if you just want to store attributes of an object
data class Character(
    val name: String = "James",
    val statNames: Array<String> = arrayOf<String>("strength", "stamina", "agility", "intellect"),
	val statValues: Array<Int> = arrayOf<Int>(6, 8, 4, 9),
    val statMap: MutableMap<String, Int> = HashMap()
) {
    init { // the init block runs when you create an instance of Character
        statNames.forEach { statName ->
            statMap[statName] = 0 // put all the statNames as keys of statMap and set values to 0
        }
    }
    
    fun print() {
        println("$name, ${statNames[0]}: ${statValues[0]}")
    }
}

fun main() {
    val myCharacter = Character()
    myCharacter.print()
  
    
    myCharacter.statMap["intellect"] = 100
    
    println(myCharacter.name)
    println(myCharacter.statValues[0])
    println(myCharacter)
    
    // can use parallel arrays to store related data
    val statNames = arrayOf<String>("strength", "stamina", "agility", "intellect")
	val statValues = arrayOf<Int>(6, 8, 4, 9)
    
    // joinToString to print the entire array
    println(statNames.joinToString())
    
    statNames[2] = "muscles"
    for (name in statNames) {
        print("$name, ")
    }    
    println()
    
    // create a map and give it initial key-value pairs
    val statMap = mutableMapOf<String, Int>(
    	"strength" to 6,
        "stamina" to 8,
        "agility" to 4,
        "intellect" to 9
    )
    val theStat = "agility"
    println(statMap)
    // access the value in statMap associated with "agility"
    println(statMap[theStat])
}