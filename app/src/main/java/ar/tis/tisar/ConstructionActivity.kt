package ar.tis.tisar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView

class ConstructionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_construction_site)
        changeVisibility()
    }

    var testData: BooleanArray = booleanArrayOf(true, true, false, false , false, false, true)

    fun changeVisibility() {
        var i: Int = 1
        var textData: TextView
        for(value: Boolean in testData) {
            when(i) {
                1 -> textData = findViewById(R.id.V1) as TextView
                2 -> textData = findViewById(R.id.V2) as TextView
                3 -> textData = findViewById(R.id.V3) as TextView
                4 -> textData = findViewById(R.id.V4) as TextView
                5 -> textData = findViewById(R.id.V5) as TextView
                6 -> textData = findViewById(R.id.V6) as TextView
                7 -> textData = findViewById(R.id.V7) as TextView
                else -> textData = findViewById(R.id.V1) as TextView
            }

            if(value) {
                textData.visibility = View.VISIBLE
            } else {
                textData.visibility = View.GONE
            }

            i++
        }
    }
}


