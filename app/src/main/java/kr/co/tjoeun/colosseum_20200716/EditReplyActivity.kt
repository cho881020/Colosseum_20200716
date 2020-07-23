package kr.co.tjoeun.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_reply.*
import kr.co.tjoeun.colosseum_20200716.utils.ServerUtil
import org.json.JSONObject

class EditReplyActivity : BaseActivity() {

//    어떤 토론에 대한 의견을 다는지 알려주는 멤버변수
    var mTopicId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_reply)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        postBtn.setOnClickListener {

//            입력 내용 저장
            val inputContent = contentEdt.text.toString()

            ServerUtil.postRequestReply(mContext, mTopicId, inputContent, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    val code = json.getInt("code")

                    if (code == 200) {
//                        의견 남기기에 성공하면 => 의견이 등록되었다는 토스트
//                        작성 화면 종료

                        runOnUiThread {
                            Toast.makeText(mContext, "의견 등록에 성공했습니다.", Toast.LENGTH_SHORT).show()

                            finish()
                        }

                    }
                    else {
//                        서버가 알려주는 의견 등록 사유를 화면에 토스트로 출력
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }


                    }

                }

            })

        }

    }

    override fun setValues() {

        topicTitleTxt.text = intent.getStringExtra("topicTitle")
        mySideTitleTxt.text = intent.getStringExtra("selectedSideTitle")

//        몇번 토론에 대한 의견 작성인지 변수로 저장
        mTopicId = intent.getIntExtra("topicId", 0)


    }

}