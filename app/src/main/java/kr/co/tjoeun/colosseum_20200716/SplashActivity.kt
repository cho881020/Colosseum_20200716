package kr.co.tjoeun.colosseum_20200716

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kr.co.tjoeun.colosseum_20200716.utils.ContextUtil

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val myHandler = Handler()

        myHandler.postDelayed({

//            저장된 토큰이 있다면 => 메인화면으로 이동
//            토큰이 빈칸이라면 => 로그인 필요 => 로그인화면

            if (ContextUtil.getLoginUserToken(mContext) == "") {
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
            }
            else {
                val myIntent = Intent(mContext, MainActivity::class.java)
                startActivity(myIntent)
            }

//            로딩화면 종료
            finish()

        }, 2500)

    }

}