fun <T> window(list: List<T>, size: Int): List<List<T>> {
    val result = mutableListOf<List<T>>()
    for (index in list.indices) {
        if (index + size > list.size) {
            break
        }
        result.add(list.subList(index, index + size))
    }
    return result
}

fun main() {
    fun part1(input: List<Int>): Int {
        var counter = 0
        var prevNUmber = 0
        input.forEach {
            if (prevNUmber != 0 && it > prevNUmber) {
                counter += 1
            }
            prevNUmber = it
        }
        return counter
    }

    fun part2(input: List<Int>): Int {
        var counter = 0
        var prevNUmber = 0
        val windows = window(input, 3)
        windows.forEach {
            val s = it.sum()
            if (prevNUmber != 0 && s > prevNUmber) {
                counter += 1
            }

            prevNUmber = s
        }
        return counter
    }

    val input = readInput("Day01").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
