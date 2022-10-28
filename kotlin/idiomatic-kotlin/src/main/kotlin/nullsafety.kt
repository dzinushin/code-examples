class Nullable {
    fun smth() {}
}
fun createNullable(): Nullable? = null

fun nullSafetyDemo() {
    createNullable()?.smth()
}