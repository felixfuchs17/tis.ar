package ar.tis.tisar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ConstructionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_construction_site)
        changeVisvibility()
    }

    fun infoToChat(v: View) {
        Log.e("TAG", "ONCLICK")
        val intent = Intent(this@ConstructionActivity, ConstructionActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    var testData: BooleanArray = booleanArrayOf(false, false, false, false, false, true, false)

    fun changeVisvibility() {
        var i = 1
        var j = 0
        var textData: TextView
        var imageData: ImageView
        for (value: Boolean in testData) {
            when (i) {
                1 -> {
                    textData = findViewById(R.id.expandedListItem1) as TextView
                    imageData = findViewById(R.id.expandedImageItem1) as ImageView
                }
                2 -> {
                    textData = findViewById(R.id.expandedListItem2) as TextView
                    imageData = findViewById(R.id.expandedImageItem2) as ImageView
                }
                3 -> {
                    textData = findViewById(R.id.expandedListItem3) as TextView
                    imageData = findViewById(R.id.expandedImageItem3) as ImageView
                }
                4 -> {
                    textData = findViewById(R.id.expandedListItem4) as TextView
                    imageData = findViewById(R.id.expandedImageItem4) as ImageView
                }
                5 -> {
                    textData = findViewById(R.id.expandedListItem5) as TextView
                    imageData = findViewById(R.id.expandedImageItem5) as ImageView
                }
                6 -> {
                    textData = findViewById(R.id.expandedListItem6) as TextView
                    imageData = findViewById(R.id.expandedImageItem6) as ImageView
                }
                else -> {
                    textData = findViewById(R.id.expandedListItem7) as TextView
                    imageData = findViewById(R.id.expandedImageItem7) as ImageView
                }

            }

            if (value) {
                textData.visibility = View.VISIBLE
                imageData.visibility = View.VISIBLE
                j++
            } else {
                textData.visibility = View.GONE
                imageData.visibility = ImageView.GONE
            }
            i++
        }
        textData = findViewById(R.id.expandedListItem8) as TextView
        imageData = findViewById(R.id.expandedImageItem8) as ImageView
        if (j == 0) {
            textData.visibility = View.VISIBLE
            imageData.visibility = View.VISIBLE
        } else {
            textData.visibility = View.GONE
            imageData.visibility = ImageView.GONE
        }
    }
}


