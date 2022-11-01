enum class Color {
    RED,
    GREEN,
    BLUE
}

enum class Steps(val value: String) {
    STEP1("step1"),
    STEP2("step2"),
}


fun enumDemo() {
    val colorRed = Color.valueOf("RED")
    println("value of 'RED' is $colorRed" )
    for( color in Color.values()) {
        println(color)
    }

    for(step in enumValues<Steps>()) {
        println("enum item: $step value ${step.value}")
    }

}
