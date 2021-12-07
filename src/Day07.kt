import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val crabs = input.first().split(",").map { it.toInt() }
        val max = crabs.maxOrNull() ?: 0
        return (0..max).map { pos ->
            crabs.map {
                abs(it - pos)
            }.sum()
        }.minOrNull() ?: 0
    }

    fun part2(input: List<String>): Int {
        val crabs = input.first().split(",").map { it.toInt() }
        val max = crabs.maxOrNull() ?: 0
        return (0..max).map { pos ->
            crabs.map {
                (1..abs(it - pos)).sum()
            }.sum()
        }.minOrNull() ?: 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
