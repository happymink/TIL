/**
 * 프로그래머스 부족한 금액 계산하기
 * 2021-08-27 김성희
 */
fun main() {
    println(solution(3, 20, 4))
}

private fun solution(price: Int, money: Int, count: Int): Long {
    val answer = - (money - (price * count.toLong() * (count.toLong() + 1) / 2)).toLong()
    return if(answer < 0) 0 else answer
}