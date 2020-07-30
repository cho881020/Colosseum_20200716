package kr.co.tjoeun.colosseum_20200716

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notification_list.*
import kr.co.tjoeun.colosseum_20200716.adapters.NotificationAdapter
import kr.co.tjoeun.colosseum_20200716.datas.Notification
import kr.co.tjoeun.colosseum_20200716.utils.ServerUtil
import org.json.JSONObject

class NotificationListActivity : BaseActivity() {

    val mNotiList = ArrayList<Notification>()
    lateinit var mNotiAdapter : NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        getNotiListFromServer()

        mNotiAdapter = NotificationAdapter(mContext, R.layout.notification_list_item, mNotiList)
        notiListView.adapter = mNotiAdapter

    }

    fun getNotiListFromServer() {

        ServerUtil.getRequestNotificationList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val notifications = data.getJSONArray("notifications")

                for (i in 0 until notifications.length()) {
//                    JSONArray 내부의 JSONObject 추출 => Notification 가공 => mNotiList 에 담자.
                    mNotiList.add(Notification.getNotificationFromJson(notifications.getJSONObject(i)))
                }

//                알림이 하나라도 있다면 => 알림을 어디까지 읽었는지 서버에 전송해주자.
//                그래야 메인화면에서 알림 갯수를 0개로 만들 수 있다.

//                알림을 어디까지 읽었는지 알려주고서는 => 아무 일도 하지 않을 예정
//                handler에 null 을 넣어서, 할일이 없다고 명시.
                ServerUtil.postRequestNotificationCheck(mContext, mNotiList[0].id, null)

                runOnUiThread {
//                    어댑터 새로고침
                    mNotiAdapter.notifyDataSetChanged()

                }

            }

        })

    }

}







