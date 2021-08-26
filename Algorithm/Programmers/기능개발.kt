import java.util.*

/**
 * 프로그래머스 기능개발
 * 2021-08-26 김성희
 */
fun main() {
    val p1 = intArrayOf(93, 30, 55)
    val p2 = intArrayOf(95, 90, 99, 99, 80, 99)

    val s1 = intArrayOf(1, 30, 5)
    val s2 = intArrayOf(1, 1, 1, 1, 1, 1)

    println("1: ${solution(p1, s1).size}")
    println("2: ${solution(p2, s2).size}")
}

private fun solution(progresses: IntArray, speeds: IntArray): IntArray {
    var answer = mutableListOf<Int>()
    val queue: Queue<Int> = LinkedList()

    for (i in progresses.indices) {
        var remainProgress = 100 - progresses[i]
        var runningTime =
            if (remainProgress % speeds[i] == 0) (remainProgress / speeds[i]) else (remainProgress / speeds[i]) + 1

        queue.add(runningTime)
    }

    var releaseDate = queue.poll()
    var dayIndex = 0
    var count = 1

    while (queue.isNotEmpty()) {
        if (queue.peek() <= releaseDate) {
            count++
            queue.poll()
        } else {
            answer.add(dayIndex, count)
            releaseDate = queue.peek()
            count = 0
            dayIndex++
        }
    }

    if(progresses.size != answer.sum()) {
        answer.add(dayIndex, count)
    }

    return answer.toIntArray()
}