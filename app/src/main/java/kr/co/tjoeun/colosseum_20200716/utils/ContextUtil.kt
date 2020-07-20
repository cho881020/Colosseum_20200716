package kr.co.tjoeun.colosseum_20200716.utils

import android.content.Context

class ContextUtil {

    companion object {

//        메모장 파일 이름처럼, 이름을 변수로 저장
        private val prefName = "ColosseumPref"

//        저장해줄 데이터 항목 이름도 변수로 저장
        private val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"

//        setter => 데이터 (토큰값) 저장 기능

        fun setLoginUserToken(context: Context, token: String) {

//            메모장을 먼저 열고
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            열린 메모장에 token을 저장.
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()

        }

//        getter => 저장된 토큰을 리턴해주는 기능

        fun getLoginUserToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_USER_TOKEN, "")!!
        }


    }

}