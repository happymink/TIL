/**
 * 프로그래머스 숫자 문자열과 영단어
 * 2021-09-04 김성희
 */
fun main() {
    println(solution("one4seveneight"))
    println(solution("23four5six7"))
    println(solution("1zerotwozero3"))
    println(solution("123"))
}

private fun solution(s: String): Int =
    s.replace("zero", "0")
        .replace("one", "1")
        .replace("two", "2")
        .replace("three", "3")
        .replace("four", "4")
        .replace("five", "5")
        .replace("six", "6")
        .replace("seven", "7")
        .replace("eight", "8")
        .replace("nine", "9")
        .toInt()