fun main() {
    fun part1(input: List<Int>): Int {
        return input.zipWithNext().count{(x, y) -> y > x}
    }

    fun part2(input: List<Int>): Int {
        return part1(input.windowed(3).map { it.sum() })
    }

    val input = readInput("Day01").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
