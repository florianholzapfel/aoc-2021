import kotlin.math.abs
import kotlin.math.max

class Point(var x: Int, var y: Int) {
    override fun toString(): String {
        return "($x, $y)"
    }
}

class Line(val start: Point, val end: Point) {
    fun isHorizontal(): Boolean {
        return start.y == end.y
    }
    fun isVertical(): Boolean {
        return start.x == end.x
    }
    override fun toString(): String {
        return "$start --> $end"
    }
}

class CoordinateSystem(dimension: Point) {
    val cs = Array(dimension.y + 1) { IntArray(dimension.x + 1) { 0 } }

    companion object {
        fun getDimension(input: List<Line>): Point {
            val max = Point(0, 0)

            input.forEach {
                if (it.start.x > max.x) max.x = it.start.x
                if (it.start.y > max.y) max.y = it.start.y
                if (it.end.x > max.x) max.x = it.end.x
                if (it.end.y > max.y) max.y = it.end.y
            }

            return max
        }
    }

    fun drawLine(line: Line, includeDiagonal: Boolean) {
        if (line.isHorizontal()) {
            if (line.start.x < line.end.x) {
                for (x in line.start.x .. line.end.x) {
                    cs[line.start.y][x] += 1
                }
            } else {
                for (x in line.start.x downTo line.end.x) {
                    cs[line.start.y][x] += 1
                }
            }
        } else if(line.isVertical()) {
            if (line.start.y < line.end.y) {
                for (y in line.start.y .. line.end.y) {
                    cs[y][line.start.x] += 1
                }
            } else {
                for (y in line.start.y downTo  line.end.y) {
                    cs[y][line.start.x] += 1
                }
            }
        } else if (includeDiagonal) {
            var dX = line.end.x - line.start.x
            var dY = line.end.y - line.start.y
            val m = max(abs(dX), abs(dY))

            dX /= m
            dY /= m

            var x = line.start.x
            var y = line.start.y

            for (n in 0..m) {
                cs[y][x] += 1
                x += dX
                y += dY
            }
        }
    }

    fun drawLines(lines: Iterable<Line>, includeDiagonal: Boolean) {
        lines.forEach{ line ->
            drawLine(line, includeDiagonal)
        }
    }

    fun countSafePassages(): Int {
        return cs.map {
            it.count{ it >= 2 }
        }.sum()
    }

    override fun toString(): String {
        return cs.map {
            it.joinToString("") { if (it == 0) "." else it.toString() }
        }.joinToString("\n")
    }
}

fun main() {
    fun readInputAsLines(name: String): List<Line> {
        val input = readInput(name)
        return input.map {
            val (start, end) = it.split("->").map {
                val (x, y) = it.trim().split(',').map { it.toInt() }
                Point(x, y)
            }
            Line(start, end)
        }
    }

    fun part1(lines: List<Line>): Int {
        val dimension = CoordinateSystem.getDimension(lines)
        val cs = CoordinateSystem(dimension)
        cs.drawLines(lines, false)

        return cs.countSafePassages()
    }

    fun part2(lines: List<Line>): Int {
        val dimension = CoordinateSystem.getDimension(lines)
        val cs = CoordinateSystem(dimension)
        cs.drawLines(lines, true)

        return cs.countSafePassages()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsLines("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInputAsLines("Day05")
    println(part1(input))
    println(part2(input))
}
