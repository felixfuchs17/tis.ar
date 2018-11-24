package ar.tis.tisar

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*
import java.net.URI

class TimeLineRecyclerAdapter(private val timeList: ArrayList<Milestones>): RecyclerView.Adapter<TimeLineRecyclerAdapter.TimeLineHolder>() {
    override fun getItemCount(): Int {
        return timeList.size
    }

    override fun onBindViewHolder(holder: TimeLineHolder, position: Int) {
        val times = timeList[position]
        holder.bindTimes(times)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row)
        return TimeLineHolder(inflatedView)
    }

    class TimeLineHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bindTimes(mileStone: Milestones){
            view.textView.text = mileStone.date
            view.arbeiter.text = mileStone.bauarbeiter
            view.activitaet.text = mileStone.activitaet
            view.photo.setImageURI(Uri.parse(mileStone.imgpath))
        }
    }

}