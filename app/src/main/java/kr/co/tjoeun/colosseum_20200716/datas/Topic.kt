package kr.co.tjoeun.colosseum_20200716.datas

import org.json.JSONObject

class Topic {
    var id = 0
    var title = ""
    var imageUrl = ""

//    주제는 선택 진영 목록을 하위 정보로 갖는다.
    val sideList = ArrayList<Side>()

//    내가 투표한 진영의 id와 데이터를 저장해두자

    var mySideId = -1
    var mySide : Side? = null // 내가 투표한 진영은 없을 수도 있다. (null 가능)

//    토론의 내 선택진영이 몇번째인지? 표현 기능
//    투표 안했으면 -1, 그 외 : 0번째 or 1번째 등.

    fun getMySideIndex() : Int {

//        내 투표와 같은 진영이 발견된 위치
//        일단 -1로 해서 발견 안되었다고 전제.
        var foundIndex = -1

//        투표를 안했다면 -1 그대로 리턴.
        if (this.mySideId == -1) {
            return foundIndex
        }

//        진영 목록을 전부 돌면서 내 투표와 같은 진영을 찾자.
//        i 가 0부터 ~ 배열의 길이만큼 반복
        for (i in this.sideList.indices) {

//            내 진영 id Vs. 선택가능진영 id 비교
            if (this.mySideId == this.sideList[i].id) {
//                같은 진영이 발견된 위치를 기록
                foundIndex = i
            }

        }


        return foundIndex

    }



    companion object {

//    json 한 덩어리를 넣으면 => Topic 객체로 변환해주는 기능

        fun getTopicFromJson(json:JSONObject) : Topic {

//            변환시켜줄 Topic 객체 생성
            val topic = Topic()

//            만든 객체의 내용물들을 json을 이용해서 채우자
            topic.id = json.getInt("id")
            topic.title = json.getString("title")
            topic.imageUrl = json.getString("img_url")

//            sides 배열에 들어있는, 진영 선택 정보도 넣어줘야함.

            val sides = json.getJSONArray("sides")

//            받아낸 jsonArray 내부를 스캔
            for (i in 0 until sides.length()) {

//                진영 정보를 하나씩 파싱해서 => 토론의 진영 목록에 추가
                val sideObj = sides.getJSONObject(i)
                val side = Side.getSideFromJson(sideObj)

                topic.sideList.add(side)

            }

//            내 선택 진영 관련 정보 파싱
            topic.mySideId = json.getInt("my_side_id")
//            서버에서 my_side에 진영정보를 넣어줄때만 파싱

            if (!json.isNull("my_side")) {

                val mySideJson = json.getJSONObject("my_side")
                topic.mySide = Side.getSideFromJson(mySideJson)

            }

//            완성된 객체를 리턴
            return topic
        }

    }


}