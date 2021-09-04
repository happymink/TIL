/**
 * 프로그래머스 직업군 추천하기
 * 2021-09-04 김성희
 */
fun main() {
    val table = arrayOf(
        "SI JAVA JAVASCRIPT SQL PYTHON C#",
        "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
        "HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
        "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
        "GAME C++ C# JAVASCRIPT C JAVA"
    )

    val languages1 = arrayOf("PYTHON", "C++", "SQL")
    val languages2 = arrayOf("JAVA", "JAVASCRIPT")
    val preference1 = intArrayOf(7, 5, 5)
    val preference2 = intArrayOf(7, 5)

    println(solution(table, languages1, preference1))
    println(solution(table, languages2, preference2))
}

private fun solution(table: Array<String>, languages: Array<String>, preference: IntArray): String {
    /**
     * 직군 별 언어 점수: 1~5 순서
     */
    val scores: List<List<String>> = listOf(
        listOf("C++", "SQL", "PYTHON", "JAVA", "JAVASCRIPT"),   // CONTENTS
        listOf("JAVA", "C", "JAVASCRIPT", "C#", "C++"),         // GAME
        listOf("JAVASCRIPT", "JAVA", "PYTHON", "C++", "C"),     // HARDWARE
        listOf("PHP", "KOTLIN", "PYTHON", "JAVASCRIPT", "JAVA"),// PORTAL
        listOf("C#", "PYTHON", "SQL", "JAVASCRIPT", "JAVA")     // SI
    )

    var max = 0
    var jobId = -1

    for(i in 0..4) {
        var score = 0

        for(j in languages.indices) {
            if(scores[i].indexOf(languages[j]) >= 0) {
                score += preference[j] * (scores[i].indexOf(languages[j]) + 1)
            }
        }

        if(score > max) {
            max = score
            jobId = i
        }
    }

    return when(jobId) {
        0 -> "CONTENTS"
        1 -> "GAME"
        2 -> "HARDWARE"
        3 -> "PORTAL"
        else -> "SI"
    }
}