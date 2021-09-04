/**
 * 프로그래머스 상호 평가
 * 2021-08-27 김성희
 */
fun main() {
    println(solution(arrayOf(intArrayOf(50, 90), intArrayOf(50, 87))))
    println(solution(arrayOf(intArrayOf(70, 49, 90), intArrayOf(68, 50, 38), intArrayOf(73, 31,100))))
}

private fun solution(scores: Array<IntArray>): String {
    var answer = ""

    for(i in scores.indices) {
        val myScore = scores[i][i]
        val mList = mutableListOf<Int>()
        for(j in scores.indices) {
            mList.add(scores[j][i])
        }
        mList.remove(myScore)
        mList.sort()

        val average = if(!mList.contains(myScore) && (myScore > mList.last() || myScore < mList[0])) {
            mList.sum() / mList.size
        } else {
            (mList.sum() + myScore) / scores.size
        }

        answer += when(average) {
            in 0..49 -> "F"
            in 50..69 -> "D"
            in 70..79 -> "C"
            in 80..89 -> "B"
            else -> "A"
        }
    }
    return answer
}