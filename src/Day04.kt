class Board(input: List<String>) {
    val rows = input.map {
        it.split(' ').filterNot{ it.isEmpty() }.map { it.toInt() }.toIntArray()
    }.toTypedArray()
    var winningNumber = 0
    var score = 0

    fun getRow(n: Int): IntArray {
        return rows[n]
    }

    fun getCol(n: Int): IntArray {
        return rows.map { it[n] }.toIntArray()
    }

    fun checkIsComplete(numbers: List<Int>): Boolean {
        for(i in 0 until 5) {
            if (numbers.containsAll(getRow(i).toList())
                || numbers.containsAll(getCol(i).toList())) {
                return true
            }
        }
        return false
    }

    fun calculateScore(numbers: List<Int>): Int {
        return rows.map {
            it.filterNot { numbers.contains(it) }.sum()
        }.sum()
    }

    fun calculateScoreUnmarked(numbers: List<Int>): Int {
        return rows.map {
            it.filter { numbers.contains(it) }.sum()
        }.sum()
    }

    override fun toString(): String {
        return rows.map{
            it.map { "%2s".format(it) }.joinToString(" ")
        }.joinToString("\n")
    }
}

fun main() {
    fun getNumbersToDraw(input: List<String>): List<Int> {
        return input.first().split(',').map{ it.toInt() }
    }
    fun toBoards(input: List<String>): List<Board> {
        val boards = mutableListOf<Board>()
        for (i in 2 until input.size step 6) {
            boards.add(Board(input.subList(i, i + 5)))
        }
        return boards
    }
    fun part1(input: List<String>): Int {
        val numbers = getNumbersToDraw(input)
        val boards = toBoards(input)
        var winningBoard: Board? = null

        for (i in 5 until numbers.size) {
            val winningNumbers = numbers.subList(0, i)
            boards.forEach{
                if (it.checkIsComplete(winningNumbers)) {
                    it.winningNumber = winningNumbers.last()
                    it.score = it.calculateScore(winningNumbers)
                    winningBoard = it
                    return@forEach
                }
            }

            if (winningBoard != null) {
                break
            }
        }

        return winningBoard!!.score * winningBoard!!.winningNumber
    }

    fun part2(input: List<String>): Int {
        val numbers = getNumbersToDraw(input)
        val boards = toBoards(input)
        val winningBoards = mutableListOf<Board>()

        for (i in 5 until numbers.size) {
            val winningNumbers = numbers.subList(0, i)
            boards.forEach{
                if (it.checkIsComplete(winningNumbers)) {
                    if (!winningBoards.contains(it)) {
                        it.winningNumber = winningNumbers.last()
                        it.score = it.calculateScore(winningNumbers)
                        winningBoards.add(it)
                    }
                    return@forEach
                }
            }
        }

        return winningBoards.last().score * winningBoards.last().winningNumber
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
