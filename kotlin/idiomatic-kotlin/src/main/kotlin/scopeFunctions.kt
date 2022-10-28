class ApplyMe {
    var str: String? = null
    var ii: Int = 0
}

fun scopeFunctionsDemo() {
    val applyMe = ApplyMe().apply {
        str = "Applied"
        ii = 10
    }
    println("applyMe: ${applyMe.str} ${applyMe.ii}")
}