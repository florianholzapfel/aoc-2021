fun main() {
    fun evolve(input: List<String>, generations: Int): ULong {
        val values = input.first().split(",").map { it.toInt() }
        val map = mutableMapOf<Int, ULong>().apply {
            for (i in 0..8) {
                this[i] = 0uL
            }
            values.forEach{
                this[it] = getValue(it) + 1uL
            }
        }

        for (gen in 1 .. generations) {
            val newFishes = map.getValue(0)

            for (i in 1..8) {
                map[i - 1] = map.getValue(i)
            }

            map[6] = map.getValue(6) + newFishes
            map[8] = newFishes
        }

        return map.values.sum()
    }

    fun part1(input: List<String>): ULong {
        return evolve(input, 80)
    }

    fun part2(input: List<String>): ULong {
        return evolve(input, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934uL)
    check(part2(testInput) == 26984457539uL)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
