package ar.tis.tisar

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.item_message.view.*
import java.net.URI

class ChatAdapter(private val timeList: ArrayList<String>): RecyclerView.Adapter<ChatAdapter.TimeLineHolder>() {
    override fun getItemCount(): Int {
        return timeList.size
    }

    override fun onBindViewHolder(holder: TimeLineHolder, position: Int) {
        val times = timeList[position]
        holder.bindTimes(times)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineHolder {
        val inflatedView = parent.inflate(R.layout.item_message)
        return TimeLineHolder(inflatedView)
    }

    class TimeLineHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bindTimes(mileStone: String){
            view.tv_message_type.text = mileStone
        }
    }

}