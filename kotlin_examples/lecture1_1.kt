/*
 * Example code from week1, lecture 1 
 */
fun main() {
    
	println("Hello world")
    
    // a val variable cannot be changed after assigned
    val anInt = 5
    println(anInt)
    
    // a var can be changed
    // not necessary, but you can declare the type
    var anotherInt: Double = 10.0
    
    println(anotherInt)
    
    anotherInt = 42.0
    // 
    println("anotherInt: $anotherInt")
    
    // when printing a member of an object, use {}
    val s = "abc"
    println("${s.length}")
}