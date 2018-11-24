package ar.tis.tisar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class ConstructionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_construction_site)
    }

    fun infoToChat(v: View) {
        Log.e("TAG", "ONCLICK")
        val intent = Intent(this@ConstructionActivity, ConstructionActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}


