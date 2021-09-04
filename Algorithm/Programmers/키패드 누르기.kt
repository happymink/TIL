/**
 * 프로그래머스 키패드 누르기
 * 2021-09-04 김성희
 */
fun main() {
    val arr1 = intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5)
    val arr2 = intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2)
    val arr3 = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)

    println(solution(arr1, "right"))
    println(solution(arr2, "left"))
    println(solution(arr3, "right"))
}

private fun solution(numbers: IntArray, hand: String): String {
    val phone = listOf(
        listOf(4, 2), // 0
        listOf(1, 1), // 1
        listOf(1, 2), // 2
        listOf(1, 3), // 3
        listOf(2, 1), // 4
        listOf(2, 2), // 5
        listOf(2, 3), // 6
        listOf(3, 1), // 7
        listOf(3, 2), // 8
        listOf(3, 3)  // 9
    )

    var answer = ""
    var left = listOf(4, 1)
    var right = listOf(4, 3)

    for(n in numbers) {
        when(n) {
            1, 4, 7 -> {
                left = phone[n]
                answer += "L"
            }
            3, 6, 9 -> {
                right = phone[n]
                answer += "R"
            }
            else -> {
                val ld = kotlin.math.abs(left[0] - phone[n][0]) + kotlin.math.abs(left[1] - phone[n][1])
                val rd = kotlin.math.abs(right[0] - phone[n][0]) + kotlin.math.abs(right[1] - phone[n][1])
                if (ld == rd) {
                    if (hand == "right") {
                        right = phone[n]
                        answer += "R"
                    } else {
                        left = phone[n]
                        answer += "L"
                    }
                } else if (ld < rd) {
                    left = phone[n]
                    answer += "L"
                } else {
                    right = phone[n]
                    answer += "R"
                }
            }
        }
    }

    return answer
}