/**
 * 프로그래머스 모의고사
 * 2021-09-10 김성희
 */
fun main() {
    println(solution(intArrayOf(1, 2, 3, 4, 5))) // [1]
    println(solution(intArrayOf(1, 3, 2, 4, 2))) // [1, 2, 3]
}

private fun solution(answers: IntArray): IntArray {
    val p1 = mutableListOf(1, 2, 3, 4, 5)
    val p2 = mutableListOf(2, 1, 2, 3, 2, 4, 2, 5)
    val p3 = mutableListOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)

    val count = mutableListOf(0, 0, 0)

    for (i in answers.indices) {
        val ans = answers[i]

        if (ans == p1[i % 5])  count[0] ++
        if (ans == p2[i % 8])  count[1] ++
        if (ans == p3[i % 10]) count[2] ++
    }

    val maxCount = count.maxOrNull()
    val answer = mutableListOf<Int>()

    for(i in 0..2) {
        if(count[i] == maxCount) answer.add(i + 1)
    }

    return answer.toIntArray()
}