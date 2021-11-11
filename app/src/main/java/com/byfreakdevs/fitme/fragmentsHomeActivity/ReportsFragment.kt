package com.byfreakdevs.fitme.fragmentsHomeActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.byfreakdevs.fitme.databinding.FragmentReportsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ReportsFragment : Fragment() {
    private var sumCalories : Double?  = 0.0
    private var sumCarbohydrates : Double? = 0.0
    private var sumProtein : Double? = 0.0
    private var sumFatsSaturated : Double? = 0.0
    private var sumFiber : Double? = 0.0
    private var sumCholesterol : Int? = 0
    private var sumSodium : Int? = 0
    private var sumSugar : Double? = 0.0


    private lateinit var binding: FragmentReportsBinding
    private var currentUser: FirebaseUser? = null
    private val rootReference = Firebase.database.reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportsBinding.inflate(layoutInflater)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentUser = FirebaseAuth.getInstance().currentUser

        val userReference = rootReference.child("Users").child(currentUser!!.uid)

        userReference.child("nutritionDetails").orderByChild("calories")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (data in dataSnapshot.children) {
                        sumCalories = sumCalories?.plus(
                            data.child("calories")
                                .getValue(Double::class.java)!!
                        )
                    }
                    val rounded = String.format("%.2f", sumCalories)
                    binding.tvCaloriesReports.text = rounded.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        userReference.child("nutritionDetails").orderByChild("carbohydrates")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (data in dataSnapshot.children) {
                        sumCarbohydrates = sumCarbohydrates?.plus(
                            data.child("carbohydrates")
                                .getValue(Double::class.java)!!
                        )
                    }
                    val rounded = String.format("%.2f", sumCarbohydrates)
                    binding.tvCarbohydratesReports.text = rounded.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        userReference.child("nutritionDetails").orderByChild("protein")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (data in dataSnapshot.children) {
                        sumProtein = sumProtein?.plus(
                            data.child("protein")
                                .getValue(Double::class.java)!!
                        )
                    }
                    val rounded = String.format("%.2f", sumProtein)
                    binding.tvProteinReports.text = rounded.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        userReference.child("nutritionDetails").orderByChild("fatsSaturated")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (data in dataSnapshot.children) {
                        sumFatsSaturated = sumFatsSaturated?.plus(
                            data.child("fatsSaturated")
                                .getValue(Double::class.java)!!
                        )
                    }
                    val rounded = String.format("%.2f", sumFatsSaturated)
                    binding.tvSaturatedFatsReports.text = rounded.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        userReference.child("nutritionDetails").orderByChild("fiber")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (data in dataSnapshot.children) {
                        sumFiber = sumFiber?.plus(
                            data.child("fiber")
                                .getValue(Double::class.java)!!
                        )
                    }
                    val rounded = String.format("%.2f", sumFiber)
                    binding.tvFiberReports.text = rounded.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        userReference.child("nutritionDetails").orderByChild("cholesterol")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (data in dataSnapshot.children) {
                        sumCholesterol = sumCholesterol?.plus(
                            data.child("cholesterol")
                                .getValue(Int::class.java)!!
                        )
                    }
                    binding.tvCholesterolReports.text = sumCholesterol.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        userReference.child("nutritionDetails").orderByChild("sodium")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (data in dataSnapshot.children) {
                        sumSodium = sumSodium?.plus(
                            data.child("sodium")
                                .getValue(Int::class.java)!!
                        )
                    }
                    binding.tvSodiumReports.text = sumSodium.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        userReference.child("nutritionDetails").orderByChild("sugar")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (data in dataSnapshot.children) {
                        sumSugar = sumSugar?.plus(
                            data.child("sugar")
                                .getValue(Double::class.java)!!
                        )
                    }
                    val rounded = String.format("%.2f", sumSugar)
                    binding.tvSugarReports.text = rounded.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
    }


}