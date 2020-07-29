package kr.co.tjoeun.colosseum_20200716.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kr.co.tjoeun.colosseum_20200716.R
import kr.co.tjoeun.colosseum_20200716.ViewReplyDetailActivity
import kr.co.tjoeun.colosseum_20200716.datas.Notification
import kr.co.tjoeun.colosseum_20200716.datas.Reply
import kr.co.tjoeun.colosseum_20200716.utils.ServerUtil
import kr.co.tjoeun.colosseum_20200716.utils.TimeUtil
import org.json.JSONObject
import java.text.SimpleDateFormat

class NotificationAdapter(
    val mContext:Context,
    resId: Int,
    val mList:List<Notification>) : ArrayAdapter<Notification>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.notification_list_item, null)
        }

        val row = tempRow!!

        val notiTitleTxt = row.findViewById<TextView>(R.id.notiTitleTxt)
        val notiCreatedAtTxt = row.findViewById<TextView>(R.id.notiCreatedAtTxt)

        val data = mList[position]

        notiTitleTxt.text = data.title

        notiCreatedAtTxt.text = TimeUtil.getTimeAgoFromCalendar(data.createAtCal)

        return row
    }

}