package kr.co.tjoeun.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.colosseum_20200716.utils.ServerUtil
import org.json.JSONObject

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
            ServerUtil.postRequestLogin(mContext, inputEmail, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

//                    로그인 성공 / 실패 여부 => code 에 있는 Int값으로 구별.
//                    200 : 로그인 성공
//                    그 외의 모든 숫자 : 로그인 실패

                    val codeNum = json.getInt("code")

                    if (codeNum == 200) {
//                        로그인 성공
                    }
                    else {
//                        로그인 실패 => 토스트로 실패했다고 출력하자.
//                        어떤 이유로 실패했는지 서버가 주는 메세지를 출력

//                        서버가 알려주는 메세지를 파싱
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

    }

}