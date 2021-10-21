package com.byfreakdevs.fitme.fragmentsHomeActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.byfreakdevs.fitme.Communicator
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.databinding.FragmentDashboardBinding
import com.byfreakdevs.fitme.models.Item
import com.byfreakdevs.fitme.networking.FoodService
import com.byfreakdevs.fitme.repository.FoodRepository
import com.byfreakdevs.fitme.utlis.DashboardRecyclerViewAdapter
import com.byfreakdevs.fitme.viewModels.FoodViewModel
import com.byfreakdevs.fitme.viewModels.FoodViewModelFactory

class DashboardFragment : Fragment() {

    lateinit var item: Item
    private var totalCarbs : Double = 0.0
    private var totalCalories : Double = 0.0

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return  binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as Communicator

        binding.recyclerViewDashboard.layoutManager = LinearLayoutManager(requireContext())
//            val data = ArrayList<DashboardItemViewModel>()
        val adapter = DashboardRecyclerViewAdapter()
        binding.recyclerViewDashboard.adapter = adapter

        val foodService = FoodService.getInstance()
//            val foodDao = ItemDatabase.getDatabase(requireContext()).getItemDao()
        val foodRepository = FoodRepository(foodService)

        binding.btGetCaloriesDashboard.setOnClickListener {

            val foodSearched = binding.etFoodDashboard.text.toString()

            val viewModel = ViewModelProvider(this , FoodViewModelFactory(foodRepository))
                .get(FoodViewModel::class.java)


            viewModel.foodList.observe(viewLifecycleOwner , Observer { list ->
                val item : Item = list[0]
                val name = item.name
                val calorie : Double = item.calories
                val carbs : Double = item.carbohydrates_total_g

                totalCarbs += carbs
                totalCalories += calorie





                communicator.passDataCom(10.0)

            })

            viewModel.foodList.observe(viewLifecycleOwner , Observer { list ->
                list?.let {
                    adapter.setFoodList(it)
                }
            })
            viewModel.getFood("$foodSearched")

            binding.etFoodDashboard.text.clear()
        }

    }
}