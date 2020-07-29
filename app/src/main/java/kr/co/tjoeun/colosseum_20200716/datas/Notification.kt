package kr.co.tjoeun.colosseum_20200716.datas

import org.json.JSONObject
import java.util.*

class Notification {

    var id = 0
    var title = ""

//    알림이 발생한 시간을 기록할 Calendar 변수
    val createAtCal = Calendar.getInstance()

    companion object {

        fun getNotificationFromJson(json : JSONObject) : Notification {
            val n = Notification()

            n.id = json.getInt("id")
            n.title = json.getString("title")

//            발생한 시간 => 차후에 작성

            return n
        }

    }

}