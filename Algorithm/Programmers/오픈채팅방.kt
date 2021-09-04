/**
 * 프로그래머스 오픈채팅방
 * 2021-09-04 김성희
 */
fun main() {
    println(
        solution(
            arrayOf(
                "Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan"
            )
        )
    )
}

private fun solution(record: Array<String>): Array<String> {
    val result = mutableListOf<String>()
    val users = mutableMapOf<String, String>()

    for(i in record.indices) {
        val r = record[i].split(" ")
        if(r.size > 2) {
            users[r[1]] = r[2]
        }
    }

    for (r in record) {
        val (_, uid, _) = r.split(" ")

        when (r[0]) {
            'E' -> { result.add("${users[uid]}님이 들어왔습니다.") }
            'L' -> { result.add("${users[uid]}님이 나갔습니다.") }
        }
    }

    return result.toTypedArray()
}