/**
 * 프로그래머스 음양 더하기
 * 2021-09-10 김성희
 */
fun main() {
    println(solution(intArrayOf(4, 7, 12), booleanArrayOf(true, false, true))) // 9
    println(solution(intArrayOf(1, 2, 3), booleanArrayOf(false, false, true))) // 0
}

private fun solution(absolutes: IntArray, signs: BooleanArray): Int =
    absolutes.mapIndexed { index, i -> if(!signs[index]) (i * -1) else i }.sum()