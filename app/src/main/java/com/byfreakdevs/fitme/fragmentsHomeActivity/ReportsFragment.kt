package com.byfreakdevs.fitme.fragmentsHomeActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.byfreakdevs.fitme.databinding.FragmentReports2Binding

class ReportsFragment : Fragment() {

    private lateinit var binding: FragmentReports2Binding

    var totalCarbsDisplay: Double? = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReports2Binding.inflate(layoutInflater)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        totalCarbsDisplay = arguments?.getDouble("key" )

//        val bundle = this.arguments

//        if (totalCarbsDisplay != null) {
        // handle your code here.
        binding.tvReports.text = totalCarbsDisplay.toString()
//        }


//        binding.tvReports.text = totalCarbsDisplay.toString()
    }
}