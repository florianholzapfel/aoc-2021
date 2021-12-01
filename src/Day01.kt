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
        return input.foldIndexed(0) { idx, acc, element ->
            if (idx > 0 && element > input[idx-1]) {
                acc + 1
            } else {
                acc
            }
        }
    }

    fun part2(input: List<Int>): Int {
        val list = window(input, 3)
        return list.foldIndexed(0) { idx, acc, element ->
            if (idx > 0 && element.sum() > list[idx-1].sum()) {
                acc + 1
            } else {
                acc
            }
        }
    }

    val input = readInput("Day01").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
