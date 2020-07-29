package kr.co.tjoeun.colosseum_20200716

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        BaseActivity를 상속받는 모든 액티비티는
//        onCreate에서 => 커스텀 액션바 설정을 하도록 하자.

//        액션바가 있는지 확인하고 실행
        supportActionBar?.let {

//            supportActionBar가 null이 아닐때 실행할 코드 블록 - let으로 지정

            setCustomActionBar()
        }

    }

//    액션바를 커스텀으로 바꿔주는 기능

    fun setCustomActionBar() {

//        액션바가 절대 null 아니라고 별개의 변수에 담자.
        val myActionBar = supportActionBar!!

//        액션바를 커스텀 액션바를 쓸 수 있도록 세팅
        myActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        myActionBar.setCustomView(R.layout.custom_action_bar)

//        액션바 뒤의 기본색 제거 => 액션바를 들고 있는 툴바의 좌우 여백(padding)을 0으로 없애자.
        val parentToolBar = myActionBar.customView.parent as Toolbar
        parentToolBar.setContentInsetsAbsolute(0, 0)


    }

}