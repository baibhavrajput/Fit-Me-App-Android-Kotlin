package com.byfreakdevs.fitme.fragmentsHomeActivity

import android.app.Activity.RESULT_OK
import android.content.Intent
//import android.app.Fragment
import android.os.Bundle
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byfreakdevs.fitme.databinding.FragmentWeightBinding
import com.byfreakdevs.fitme.utlis.*
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class WeightFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etDate: EditText
    private lateinit var etWeight: EditText
    private lateinit var btnSave: Button
    private var weightDetailsArrayList = ArrayList<WeightDetails>()
    private var currentUser: FirebaseUser? = null
    var TAG : String = "TAG"
    private lateinit var binding: FragmentWeightBinding
    private val rootReference = Firebase.database.reference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeightBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        database = FirebaseDatabase.getInstance().reference
//        firebaseAuth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(requireContext())

        recyclerView = binding.recyclerView
        etDate = binding.etDate
        btnSave = binding.btnSave
        etWeight = binding.etWeight

        val mDatabase = FirebaseDatabase.getInstance().reference
        currentUser = FirebaseAuth.getInstance().currentUser

        val recyclerViewAdapter = WeightRecyclerViewAdapter(weightDetailsArrayList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerViewAdapter

        val userReference = rootReference.child("Users").child(currentUser!!.uid)

        btnSave.setOnClickListener {
            userReference.child("weightDetails").push()
                .setValue(WeightDetails(etDate.text.toString(), etWeight.text.toString()))

            etWeight.text.clear()
            etDate.text.clear()
        }

        userReference.child("weightDetails").addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {

                val weightDetails = dataSnapshot.getValue(WeightDetails::class.java)
                weightDetailsArrayList.add(WeightDetails(weightDetails!!.date!!, weightDetails.weight!!))
                recyclerViewAdapter.notifyDataSetChanged()
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        e(TAG, currentUser!!.displayName!!)
        e(TAG,currentUser!!.uid)
    }
}
