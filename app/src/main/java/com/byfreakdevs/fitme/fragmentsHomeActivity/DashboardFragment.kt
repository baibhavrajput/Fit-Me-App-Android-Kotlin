package com.byfreakdevs.fitme.fragmentsHomeActivity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byfreakdevs.fitme.databinding.FragmentDashboardBinding
import com.byfreakdevs.fitme.models.Item
import com.byfreakdevs.fitme.networking.FoodService
import com.byfreakdevs.fitme.repository.FoodRepository
import com.byfreakdevs.fitme.ui.HomeActivity
import com.byfreakdevs.fitme.utlis.FoodDetails
import com.byfreakdevs.fitme.utlis.DashboardRecyclerViewAdapter
import com.byfreakdevs.fitme.utlis.NutritionDetails
import com.byfreakdevs.fitme.viewModels.FoodViewModel
import com.byfreakdevs.fitme.viewModels.FoodViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class DashboardFragment : Fragment() {

    private var calories = 0.0
    private var carbohydrates = 0.0
    private var protein = 0.0
    private var fiber = 0.0
    private var fatsSaturated = 0.0
    private var cholesterol = 0
    private var sodium = 0
    private var sugar = 0.0
    private var name = ""
    private var sumCalories = 0.0

    private lateinit var recyclerView: RecyclerView
    private var foodDetailsArrayList = ArrayList<FoodDetails>()
    private var currentUser: FirebaseUser? = null
    private val rootReference = Firebase.database.reference

    lateinit var item: Item

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerViewDashboard
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val recyclerViewAdapter = DashboardRecyclerViewAdapter(foodDetailsArrayList)
        recyclerView.adapter = recyclerViewAdapter

        currentUser = FirebaseAuth.getInstance().currentUser
        val userReference = rootReference.child("Users").child(currentUser!!.uid)

        val foodService = FoodService.getInstance()
        val foodRepository = FoodRepository(foodService)

        binding.btDeleteDashboard.setOnClickListener {
//            userReference.child("foodDetails").removeValue()
//            userReference.child("nutritionDetails").removeValue()
            userReference.child("nutritionDetails").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.ref.removeValue()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

            userReference.child("foodDetails").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.ref.removeValue()
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

            recyclerViewAdapter.notifyDataSetChanged()

        }


        binding.btGetCaloriesDashboard.setOnClickListener {

            val foodSearched = binding.etFoodDashboard.text.toString()

            val viewModel = ViewModelProvider(this , FoodViewModelFactory(foodRepository))
                .get(FoodViewModel::class.java)

            viewModel.getFood("$foodSearched")

            viewModel.foodList.observe(viewLifecycleOwner , Observer { list ->

                val item : Item = list[0]
                calories = item.calories
                carbohydrates = item.carbohydrates_total_g
                fatsSaturated = item.fat_saturated_g
                fiber = item.fiber_g
                name = foodSearched
                protein = item.protein_g
                sugar = item.sugar_g
                cholesterol = item.cholesterol_mg
                sodium = item.sodium_mg

                userReference.child("foodDetails").push()
                    .setValue(FoodDetails(name , calories ))

                userReference.child("nutritionDetails").push()
                    .setValue(NutritionDetails( calories , carbohydrates , protein , fatsSaturated , fiber , cholesterol , sodium , sugar))

            })

            binding.etFoodDashboard.text.clear()

        }
        userReference.child("foodDetails").addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {

                val foodDetails = dataSnapshot.getValue(FoodDetails::class.java)
                foodDetailsArrayList.add(FoodDetails(foodDetails!!.name, foodDetails.calories!!))
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

        userReference.child("nutritionDetails").orderByChild("calories").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (data in dataSnapshot.children) {
                    sumCalories = sumCalories?.plus(
                        data.child("calories")
                            .getValue(Double::class.java)!!
                    )
                }
                val rounded = String.format("%.2f", sumCalories)
                binding.tvTotalDashboard.text = rounded.toString()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }
}