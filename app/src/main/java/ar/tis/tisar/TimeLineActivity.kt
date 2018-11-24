package ar.tis.tisar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_time_line.*
import java.io.Serializable

class TimeLineActivity: AppCompatActivity() {

    // For RecyclerView
    private lateinit var adapter: TimeLineRecyclerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)
        val milestone11 = Milestones("17.08.11-22.11.12", "Peter Paul", "Rohrreinigung", "res/drawable/baumann.jpg")
        val milestone12 = Milestones("02.02.2015-01.07.2017", "Karl Heinz", "Kabel", "res/drawable/baumann1.jpg")
        val milestone13 = Milestones("01.06.10-31.12.18", "Karl Kurbel", "Asphaltierung", "res/drawable/baumann3.jpg")

        val timesList: ArrayList<Milestones> = ArrayList()
        timesList.add(milestone11)
        timesList.add(milestone12)
        timesList.add(milestone13)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        adapter = TimeLineRecyclerAdapter(timesList)
        recyclerView.adapter = adapter

    }

    override fun onBackPressed() {
        val intent = Intent(this@TimeLineActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}

data class Milestones(var date: String,var bauarbeiter: String,var activitaet: String,var imgpath: String): Serializable