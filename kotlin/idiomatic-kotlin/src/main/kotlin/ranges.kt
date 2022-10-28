

class Version(val major: Int, val minor: Int): Comparable<Version> {
    override fun compareTo(other: Version): Int {
        if (this.major != other.major) {
            return this.major - other.major
        }
        return this.minor - other.major
    }
}

fun rangesDemo() {
    val versionRange = Version(1,11)..Version(1,30)
    println(Version(0,19) in versionRange)
    println(Version(1,12) in versionRange)

    val doubleRange = 0.0..10.0
    println(0.3 in doubleRange)
}

