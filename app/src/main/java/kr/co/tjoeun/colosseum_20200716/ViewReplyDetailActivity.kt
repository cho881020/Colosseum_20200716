package kr.co.tjoeun.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ViewReplyDetailActivity : BaseActivity() {

//    보려는 의견의 id는 여러 함수에서 공유할 것 같다.
//    그래서 멤버변수로 만들고 저장한다.
    var mReplyId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reply_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        의견 리스트뷰에서 보내준 id값을 멤버변수에 담아주자.
        mReplyId = intent.getIntExtra("replyId", 0)

    }

}