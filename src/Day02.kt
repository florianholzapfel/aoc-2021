fun main() {
    fun part1(input: List<Pair<String, Int>>): Int {
        val (forward, depth) = input.fold(Pair(0, 0)) { acc, element ->
            val (command, value) = element
            val (forward, depth) = acc
            when (command) {
                "forward" -> Pair(forward + value, depth)
                "up" -> Pair(forward, depth - value)
                "down" -> Pair(forward, depth + value)
                else -> Pair(forward, depth)
            }
        }
        return forward * depth
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        val (forward, depth, _) = input.fold(Triple(0, 0, 0)) { acc, element ->
            val (command, value) = element
            val (forward, depth, aim) = acc
            when (command) {
                "forward" -> Triple(forward + value, depth + (aim * value), aim)
                "up" -> Triple(forward, depth, aim - value)
                "down" -> Triple(forward, depth, aim + value)
                else -> Triple(forward, depth, aim)
            }
        }
        return forward * depth
    }

    fun readAndTransformInput(name: String): List<Pair<String, Int>> {
        return readInput(name).map{
            val (command, value) = it.split(' ')
            Pair(command, value.toInt())
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readAndTransformInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readAndTransformInput("Day02")
    println(part1(input))
    println(part2(input))
}
