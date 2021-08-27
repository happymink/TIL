import java.util.*

/**
 * 프로그래머스 짝지어 제거하기
 * 2021-08-24 김성희
 */
fun main() {
    val str1 = "abbabb"
    val str2 = "cbcb"

    println(solution(str1))
    println(solution(str2))
}

private fun solution(str: String) : Int{
    if(str.length % 2 == 1 || str[0] == ')') return 0

    val stack = Stack<Char>()

    for(c in str) {
        if(stack.isEmpty() || stack.peek() != c) {
            stack.add(c)
        } else {
            stack.pop()
        }
    }

    return if(stack.isEmpty()) 1 else 0
}