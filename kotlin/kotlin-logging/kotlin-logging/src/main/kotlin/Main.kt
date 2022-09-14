import mu.KotlinLogging

val log = KotlinLogging.logger {  }
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    try {
        val pi = 3.1415
        log.info { "Logging in info level pi: $pi" }

        val z = 1/0
    } catch (e : Exception) {
        log.error(e) { "logging error info with exception: " }
    }
}
