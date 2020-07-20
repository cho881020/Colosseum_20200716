package kr.co.tjoeun.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.tjoeun.colosseum_20200716.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        okBtn.setOnClickListener {

//            닉네임 / 이메일은 비어있으면 안된다.

            val inputEmail = emailEdt.text.toString()

            if (inputEmail.isEmpty()) {
                Toast.makeText(mContext, "이메일은 반드시 입력해야합니다.", Toast.LENGTH_SHORT).show()
//                밑의 코드는 실행할 필요가 없다.
//                이벤트 처리를 강제 종료.
                return@setOnClickListener
            }

            val inputNickName = nickNameEdt.text.toString()

            if (inputNickName.isEmpty()) {
                Toast.makeText(mContext, "닉네임은 반드시 입력해야합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            비번은 8글자 이내면 안됨.

            val inputPassword = passwordEdt.text.toString()

            if (inputPassword.length < 8) {
                Toast.makeText(mContext, "비밀번호는 최소 8글자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            모든 검사를 통과하면, 서버에 가입 요청
//            여기 코드가 실행된다 => 모든 검사를 통과했다.

            ServerUtil.putRequestSignUp(mContext, inputEmail, inputPassword, inputNickName, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    val code = json.getInt("code")

                    if (code == 200) {
//                        회원가입 성공 => 토스트로 가입 성공메세지 + 로그인 복귀

                        runOnUiThread {
                            Toast.makeText(mContext, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }

                    }
                    else {
//                        가입 실패 => 서버가 알려주는 실패사유를 토스트로 출력

                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }


                    }

                }

            })


        }

//        비밀번호 입력 내용 변경 이벤트 처리
        passwordEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                내용 변경 완료된 시점에 실행

//                입력된 글자의 길이 확인.
//                비어있다면, "비밀번호를 입력해 주세요."
//                8글자 안되면, "비밀번호가 너무 짧습니다."
//                그 이상이면, "사용해도 좋은 비밀번호입니다."

                val tempPw = passwordEdt.text.toString()

                if (tempPw.isEmpty()) {
//                    입력 안한 경우
                    passwordCheckResultTxt.text = "비밀번호를 입력해 주세요."
                }
                else if (tempPw.length < 8) {
//                    길이가 부족한 경우
                    passwordCheckResultTxt.text = "비밀번호가 너무 짧습니다."
                }
                else {
//                    충분히 긴 비밀번호
                    passwordCheckResultTxt.text = "사용해도 좋은 비밀번호 입니다."
                }


            }

        })

    }

    override fun setValues() {

    }


}