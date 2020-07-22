package kr.co.tjoeun.colosseum_20200716.datas

import org.json.JSONObject

class Reply {

    var id = 0
    var content = ""
    lateinit var writer : User
    lateinit var selectedSide : Side

    companion object {

//        JSONObject 하나를 넣으면 => 의견 내용을 파싱해서 Reply로 리턴하는 기능

        fun getReplyFromJson(json: JSONObject) : Reply {
            val r = Reply()

            r.id = json.getInt("id")
            r.content = json.getString("content")

//            작성자 / 선택진영 => JSONObject를 받아서 곧바로 대입
            r.writer = User.getUserFromJson(json.getJSONObject("user"))

            r.selectedSide = Side.getSideFromJson(json.getJSONObject("selected_side"))

            return r
        }


    }

}