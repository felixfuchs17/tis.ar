package ar.tis.tisar


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.tis.tisar.R.id.floatingButtonLeft
import android.support.design.widget.FloatingActionButton
import android.util.Log


class ARFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view=inflater.inflate(R.layout.fragment_ar, container, false)


        return view
    }



}
