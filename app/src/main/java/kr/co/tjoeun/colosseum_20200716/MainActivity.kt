package kr.co.tjoeun.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.colosseum_20200716.utils.ServerUtil

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        loginBtn.setOnClickListener {

//            입력한 아이디와 비번을 받아서
            val inputEmail = emailEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            서버에 전달해주고 응답 처리
            ServerUtil.postRequestLogin(mContext, inputEmail, inputPw, null)

        }
    }

    override fun setValues() {

    }

}