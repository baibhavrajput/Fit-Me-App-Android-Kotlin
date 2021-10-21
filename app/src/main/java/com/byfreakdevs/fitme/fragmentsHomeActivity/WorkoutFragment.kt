package com.byfreakdevs.fitme.fragmentsHomeActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.databinding.FragmentWorkoutBinding
import com.byfreakdevs.fitme.fragmentsWorkout.*
import com.byfreakdevs.fitme.utlis.ViewPagerAdapterWorkout
import com.google.android.material.tabs.TabLayoutMediator

class WorkoutFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutBinding

//    private val chestFragment = ChestFragment()
//    private val backFragment = BackFragment()
//    private val legsFragment = LegsFragment()
//    private val bicepsFragment = BicepsFragment()
//    private val tricepsFragment = TricepsFragment()
//    private val shoulderFragment = ShoulderFragment()


//    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWorkoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabList = listOf("CHEST", "BACK", "LEGS", "BICEPS", "TRICEPS", "SHOULDER")

        val list = ArrayList<Fragment>()
        list.add(ChestFragment())
        list.add(BackFragment())
        list.add(LegsFragment())
        list.add(BicepsFragment())
        list.add(TricepsFragment())
        list.add(ShoulderFragment())

        binding.viewPagerWorkout.adapter =
            ViewPagerAdapterWorkout(childFragmentManager, lifecycle, list)

        TabLayoutMediator(binding.tabLayoutWorkout, binding.viewPagerWorkout) { tab, position ->
            tab.text = tabList[position]
        }.attach()


    }



//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }

}