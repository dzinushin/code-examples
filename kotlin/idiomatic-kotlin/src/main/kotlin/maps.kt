

val testMap = hashMapOf(
    "key1" to "val1",
    "key2" to "val2"
)


fun demoMaps() {

    val key1InMap = "key1" in testMap
    println("key1 in map: $key1InMap")
    val unknownKeyInMap = "kkk" in testMap
    println("kkk in map: $unknownKeyInMap")

}
