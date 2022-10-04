package com.byfreakdevs.fitme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.byfreakdevs.fitme.databinding.FragmentProfileBinding
import com.byfreakdevs.fitme.utlis.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private var rootReference = Firebase.database.reference
    private var currentUser: FirebaseUser? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentUser = FirebaseAuth.getInstance().currentUser

        val userReference = rootReference.child("Users").child(currentUser!!.uid)

//        Log.d("sonusourav","uid = ${firebaseAuth.currentUser!!.uid}")

        userReference.child("userDetails").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)!!
//                Toast.makeText(this@HomeActivity, "hey ${user.userName}",Toast.LENGTH_SHORT).show()
                binding.tvNameProfile.text = user.userName
                binding.tvUserNameProfile.text = user.userId.toString()
                binding.tvEmailProfile.text = user.userEmail
                binding.tvUniqueIdProfile.text = user.userFirebaseId

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}