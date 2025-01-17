/*
 * Example code from week1, lecture 2 
 */
fun main() {
	testConditional()
    testLists()
    testArrays()
    testNull()
}

fun testNull() {
    // to make a variable that can be null, use ?
    var numBooks: Int? = null
    
    // since numBooks is null, to call a method we need to 
    // use ?.method() to safely check for null
    numBooks = numBooks?.dec() ?: 0
    // ?: is called the Elvis operator   
    // kinda like ternary op
    // numBooks > 5 ? true : false
    
    println(numBooks)
    
    numBooks = 7
    // if we don't want to do a safe check for null
    // we can use !!.method()
    // if numBooks is null an exception is thrown
    numBooks = numBooks!!.dec()
    println(numBooks)
}

fun testArrays() {
    // an array is fixed length so pets can only 
    // have 3 elements
    val pets = arrayOf("dog", "cat", "canary")
    // we can print arrays using the toString
    println(java.util.Arrays.toString(pets))
    
    // we can combine arrays using +
    val numbers = intArrayOf(1,2,3)
    val numbers2 = intArrayOf(4,5,6)
    val combined = numbers2 + numbers
    println(java.util.Arrays.toString(combined))
}

fun testLists() {
    // need to specify mutableListOf if we want to be able to
    // add, remove, or insert into the list
    val instruments = mutableListOf("trumpet", "piano", "violin")
    
    // easy way to print the list
    println(instruments)
    println(instruments[1])
    
    // even though instruments is a 'val', we can modify the elements
    instruments[1] = "guitar"
    println(instruments)
    
    // we can also add to the end
    instruments.add("drums")
	println(instruments)
    
    // or insert at a specific spot
    instruments.add(2, "bass")
   	println(instruments)






    
}

fun testConditional() {
    println("Hello world")
    
    var length: Double = 2.5
    var width = 12.0
    
  	var numDogs = 0
    val numCats = 21
    
    if (numDogs > numCats) {
        println("Good number of dogs")
    } else if (numDogs == numCats) {
		println("Too many cats")
    } else {
        println("Way too many cats")
    }
    
    if (numDogs in 1..50) {
        println("Not enough dogs")
    }
    
    when (numDogs) {
        0 -> println("NO DOGS!!")
        in 1..39 -> println("Ok num of dogs")
        else -> println("num dogs is sufficient")
    }
    
    numDogs = 20
    
     val dogStatement = when (numDogs) {
        0 -> "NO DOGS!!"
        in 1..39 -> "Ok num of dogs"
        else -> "num dogs is sufficient"
    }
     
     println(dogStatement)
     
     val pets = arrayOf("dog", "cat", "canary")
     for (pet in pets) {
         print(pet + " ")
     }
     println()
	for ((index, pet) in pets.withIndex()) {
         println("index: $index pet: $pet")
     }
}