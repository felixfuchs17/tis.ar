package ar.tis.tisar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var viewAdapter: ChatAdapter
    private lateinit var viewManager:RecyclerView.LayoutManager
val messages: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        recyclerView = findViewById<RecyclerView>(R.id.rv_chat_messages).apply{
            setHasFixedSize(true)
            layoutManager=viewManager
            adapter =viewAdapter
        }

        addmessages()

        rv_chat_messages.layoutManager = LinearLayoutManager(this)
        rv_chat_messages.adapter = ChatAdapter(messages)
    }

    fun addmessages() {
        var messagesc = 0
        while (messagesc<20) {
            messages.add("Hallo Welt!" + messagesc)
            messagesc++
        }
    }
}

