fun main() {
    fun getGamma(input: List<String>): Int {
        val size = input.first().length
        var result = ""

        for (i in 0 until size) {
            val ones = input.count { it[i] == '1' }
            val zeros = input.count { it[i] == '0' }
            result = if (ones > zeros) result.plus("1") else result.plus("0")
        }

        return result.toInt(2)
    }

    fun getEpsilon(input: List<String>): Int {
        val size = input.first().length
        var result = ""

        for (i in 0 until size) {
            val ones = input.count { it[i] == '1' }
            val zeros = input.count { it[i] == '0' }
            result = if (ones < zeros) result.plus("1") else result.plus("0")
        }

        return result.toInt(2)
    }

    fun getOxygenGeneratorRating(input: List<String>): Int {
        val size = input.first().length
        var result = input

        for (i in 0 until size) {
            val ones = result.filter { it[i] == '1' }
            val zeros = result.filter { it[i] == '0' }
            result = if (ones.size >= zeros.size) ones else zeros
        }

        return result[0].toInt(2)
    }

    fun getCo2ScrubberRating(input: List<String>): Int {
        val size = input.first().length
        var result = input

        for (i in 0 until size) {
            val ones = result.filter { it[i] == '1' }
            val zeros = result.filter { it[i] == '0' }
            result = if (ones.size < zeros.size) ones else zeros

            if (result.size == 1) break;
        }

        return result[0].toInt(2)
    }

    fun part1(input: List<String>): Int {
        val gamma = getGamma(input)
        val epsilon = getEpsilon(input)
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = getOxygenGeneratorRating(input)
        val co2ScrubberRating = getCo2ScrubberRating(input)
        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
