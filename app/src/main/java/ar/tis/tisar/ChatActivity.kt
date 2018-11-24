package ar.tis.tisar


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_chat.*
import java.io.Serializable

class ChatActivity: AppCompatActivity() {

    // For RecyclerView
    private lateinit var adapter: ChatAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val timesList: ArrayList<String> = ArrayList()
        addmessages("hallo",timesList)

        rv_chat_messages.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        adapter = ChatAdapter(timesList)
        rv_chat_messages.adapter = adapter

    }
}

    fun addmessages(messageString: String,messages: ArrayList<String>) {
        var messagesc = 0
        messages.add(messageString)
        while (messagesc<20) {
            messages.add("Hallo Welt!" + messagesc)
            messagesc++
        }
    }


