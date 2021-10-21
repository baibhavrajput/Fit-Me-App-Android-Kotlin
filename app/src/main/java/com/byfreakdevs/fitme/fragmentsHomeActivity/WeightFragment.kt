package com.byfreakdevs.fitme.fragmentsHomeActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.byfreakdevs.fitme.models.WeightItemViewModel
import com.byfreakdevs.fitme.utlis.WeightData
import com.byfreakdevs.fitme.utlis.WeightRecycleViewAdapter
import com.byfreakdevs.fitme.databinding.FragmentWeight2Binding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class WeightFragment : Fragment() {

    lateinit var database: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: FragmentWeight2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeight2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewWeight.layoutManager = LinearLayoutManager(requireContext())
        val data = ArrayList<WeightItemViewModel>()
        val adapter = WeightRecycleViewAdapter(data)

        database = FirebaseDatabase.getInstance().reference
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btSaveWeight.setOnClickListener {

            val date = binding.etDate.text.toString()
            val weight = binding.etWeight.text.toString()

            firebaseDatabase()

            data.add(WeightItemViewModel("$date" , "$weight"))

            binding.recyclerViewWeight.adapter = adapter

            binding.etWeight.text.clear()
            binding.etDate.text.clear()

        }

    }
    private fun firebaseDatabase( ) {

        val userFirebaseId = firebaseAuth.currentUser!!.uid
        val date = binding.etDate.text.toString()
        val weight = binding.etWeight.text.toString()

        database = FirebaseDatabase.getInstance().getReference("Users")
        val weightData = WeightData( date , weight)
        database.child(userFirebaseId).child("weightDetails").push().setValue(weightData)
    }

}