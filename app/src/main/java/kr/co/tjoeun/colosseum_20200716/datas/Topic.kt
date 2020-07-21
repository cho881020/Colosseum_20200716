package kr.co.tjoeun.colosseum_20200716.datas

import org.json.JSONObject

class Topic {
    var id = 0
    var title = ""
    var imageUrl = ""

    companion object {

//    json 한 덩어리를 넣으면 => Topic 객체로 변환해주는 기능

        fun getTopicFromJson(json:JSONObject) : Topic {

//            변환시켜줄 Topic 객체 생성
            val topic = Topic()

//            만든 객체의 내용물들을 json을 이용해서 채우자
            topic.id = json.getInt("id")
            topic.title = json.getString("title")
            topic.imageUrl = json.getString("img_url")

//            완성된 객체를 리턴
            return topic
        }

    }


}