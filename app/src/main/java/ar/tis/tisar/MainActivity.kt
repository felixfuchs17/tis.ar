package ar.tis.tisar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import ar.tis.tisar.helper.ARLocationPermissionHelper


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARLocationPermissionHelper.requestPermission(this)
    }



    

}
