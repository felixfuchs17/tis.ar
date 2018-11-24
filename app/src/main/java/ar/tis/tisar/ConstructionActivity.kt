package ar.tis.tisar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import ar.tis.tisar.helper.DataStructure

class ConstructionActivity : AppCompatActivity() {
    var defaulConstructionSite : DataStructure = DataStructure(1, "RWE", "Hans Mueller", "0190", "P-18-025 Floßstraße Umleitung",
        "Kabel legen", "Bosch", "Oliver Kahn", "1234", "01.01.1989", "100 Wochen", true, false, true, false, false, false, false)
    var defaultConstructionSiteB : DataStructure = DataStructure(1, "RWE AG", "Herr Klaßen", "0157 453123", "neue Wasserleitungen", "Rohrbruch", "Hans Wasser GmbH", "Herr Zil", "4556 128734", "20.05.2018", "10. Monate", true, true, false, false, false, false, true)
    var defaultConstructionSiteC : DataStructure = DataStructure(2, "Unitymedia AG", "Herr Berg", "0157 4345433", "neue Ethernetkabel", "Netzwerkumbau", "net-Worker GmbH", "Herr Salim", "4556 374534", "20.05.2019", "36. Monate", true, true, false, false, false, false, false)
    var defaultConstructionSiteD : DataStructure = DataStructure(
        3, "Aurelius AG", "Herr Matterhorn", "0157 6783243", "wechsel aller älteren Rohre", "Instandhaltung der Infrastruktur", "Siegbert GmbH", "Herr Marsi", "4556 545364", "30.12.2016", "24. Monate", false, false, false, false, false, false, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_construction_site)
        changeConstructionSite(defaulConstructionSite)
        changeVisvibility()
    }

    fun infoToChat(v: View) {
        Log.e("TAG", "ONCLICK")
        val intent = Intent(this@ConstructionActivity, ConstructionActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    var testData: BooleanArray = booleanArrayOf(false, false, false, false, false, true, false)

    fun changeConstructionSite(site: DataStructure) {
        var supervisorView = findViewById(R.id.construction_supervisor) as TextView
        supervisorView.setText(site.bauleiter)
        var beginView = findViewById(R.id.construction_begin) as TextView
        beginView.setText(site.baubeginn)
        var durationView = findViewById(R.id.construction_duration) as TextView
        durationView.setText(site.baudauer)
        var projectView = findViewById(R.id.construction_project) as TextView
        projectView.setText(site.baumassnahme)
        var contactView = findViewById(R.id.construction_contact) as TextView
        contactView.setText(site.telefonBau)
        this.testData.set(0, site.v1)
        this.testData.set(1, site.v2)
        this.testData.set(2, site.v3)
        this.testData.set(3, site.v4)
        this.testData.set(4, site.v5)
        this.testData.set(5, site.v6)
        this.testData.set(6, site.v7)
    }

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


