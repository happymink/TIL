import java.util.*

/**
 * 프로그래머스 올바른 괄호
 * 2021-08-24 김성희
 */
fun main() {
    println(solution("((())("))
}

private fun solution(str: String): Boolean {
    if(str.length % 2 == 1) return false

    val stack = Stack<Char>()
    for(c in str) {
        if(stack.isEmpty() || stack.peek() == c) {
             stack.add(c)
        } else {
            stack.pop()
        }
    }

    return stack.isEmpty()
}