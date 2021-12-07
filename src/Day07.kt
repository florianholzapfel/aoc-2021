import kotlin.math.abs

fun main() {
    fun moveCrabs(input: List<String>, calculateFuel: (crab: Int, pos: Int) -> Int): Int {
        val crabs = input.first().split(",").map { it.toInt() }
        val max = crabs.maxOrNull() ?: 0
        return (0..max).minOfOrNull { pos ->
            crabs.sumOf {
                calculateFuel(it, pos)
            }
        } ?: 0
    }

    fun part1(input: List<String>): Int {
        return moveCrabs(input){ crab, pos ->
            abs(crab - pos)
        }
    }

    fun part2(input: List<String>): Int {
        return moveCrabs(input){ crab, pos ->
            (1..abs(crab - pos)).sum()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
