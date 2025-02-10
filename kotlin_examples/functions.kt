/* 
    Kotlin Functions
*/

fun printHello(number: Int, name: String = "No Name"): String {
    return "Hello $name $number"
}

fun encodeMsg(msg: String, encode: (String) -> String) : String {
	return encode(msg)
}

fun doTheLambda(num: Int, lamb: (Int)->Unit) {
    lamb(num)
}

fun main() {
   val result = printHello(name = "James", number = 42)
   println(result)
   
   var dirtLevel: Int = 20
   //val waterFilter = {level: Int -> level / 2} 
   val waterFilter: (Int) -> Int = {level -> level / 2}
   
   println(waterFilter(dirtLevel))
   
   val msg = "my message"
   val encode = {theMessage: String -> theMessage.toUpperCase()}
   val anotherEncode = {s: String -> s.toLowerCase()}
   val anotherMsg = "ALL CAPS"
   println(encodeMsg(anotherMsg, anotherEncode))
   
   val encodeMsgResult = encodeMsg(msg = "james") {s -> 
       s.toUpperCase()
   }
   
   println(encodeMsgResult)
   
   var myNum = 8
   println(myNum)
   doTheLambda(num = 23,lamb = {n: Int -> myNum = n})
   println(myNum)
 
 	
 
}