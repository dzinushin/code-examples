data class Person(val name: String, val age:Int)

fun lambdasDemo() {
    val sum = { x: Int, y: Int -> x + y }
    println("sum 1, 2: ${sum(1,2)}");
    { println(42) }()

    run {
        println(42)
    }

    val persons = listOf(Person("Young", 21), Person("Old", 31))

    println("Oldest person: ${persons.maxBy({p: Person -> p.age})}")
    println("Oldest person: ${persons.maxBy() { p: Person -> p.age } }")
    println("Oldest person: ${persons.maxBy( { it.age } ) }")
    println("Oldest person: ${persons.maxBy { it.age }}")
    println("Oldest person: ${persons.maxBy(Person::age)}")


    println( persons.joinToString { it.name })
}
