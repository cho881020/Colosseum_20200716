package kr.co.tjoeun.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.tjoeun.colosseum_20200716.datas.Topic
import kr.co.tjoeun.colosseum_20200716.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<Topic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        getTopicListFromServer()
    }

    fun getTopicListFromServer() {

        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")

//                topics는 [ ] 임. => JSONArray 로 추출해야함.
                val topics = data.getJSONArray("topics")

//                topics 내부에는 JSONObject 가 여러개 반복으로 들어있다.
//                JSON을 들고 있는 배열 => JSONArray

//                for문을 이용해서, topics 내부의 데이터를 하나씩 추출.
//                i가 0부터 ~ topics의 갯수 직전. 4개 : (0,1,2,3)

                for (i in 0 until topics.length()) {

//                    topics 내부의 데이터를 JSONObject로 추출
                    val topicObj = topics.getJSONObject(i)

//                    topicObj => Topic 형태의 객체로 변환

                    val topic = Topic.getTopicFromJson(topicObj)

//                    변환된 객체를 목록에 추가
                    mTopicList.add(topic)

                }

            }

        })

    }


}