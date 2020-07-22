package kr.co.tjoeun.colosseum_20200716.datas

import org.json.JSONObject

class User {

    var id = 0
    var email = ""
    var nickName = ""

    companion object {

        fun getUserFromJson(json : JSONObject) : User {
            val u = User()
            
//            사용자 정보를 파싱하는 코드
            u.id = json.getInt("id")
            u.email = json.getString("email")
            u.nickName = json.getString("nick_name")
            
            return u
        }

    }

}