package com.byfreakdevs.fitme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.byfreakdevs.fitme.*
import com.byfreakdevs.fitme.databinding.ActivityHomeBinding
import com.byfreakdevs.fitme.fragmentsHomeActivity.DashboardFragment
import com.byfreakdevs.fitme.fragmentsHomeActivity.ReportsFragment
import com.byfreakdevs.fitme.fragmentsHomeActivity.WeightFragment
import com.byfreakdevs.fitme.fragmentsHomeActivity.WorkoutFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var firebaseAuth : FirebaseAuth
    private val rootReference = Firebase.database.reference

    private val dashboardFragment = DashboardFragment()
    private val workoutFragment = WorkoutFragment()
    private val reportsFragment = ReportsFragment()
    private val weightFragment = WeightFragment()
    private val aboutFragment = AboutFragment()
    private val profileFragment = ProfileFragment()
    private val feedbackFragment = FeedbackFragment()
    private val shareFragment = ShareFragment()
    private val settingsFragment = SettingsFragment()


    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        /** Initialize Firebase Auth */
        firebaseAuth = FirebaseAuth.getInstance()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationMenuHome)
//        val navController = findNavController(R.id.fragmentContainerViewHome)
        val navView = findViewById<NavigationView>(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout , R.string.open , R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        bottomNavigationView.setupWithNavController(navController)
        replaceFragment(dashboardFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.workoutFragment -> replaceFragment(workoutFragment)
            }
            when(it.itemId){
                R.id.dashboardFragment -> replaceFragment(dashboardFragment)
            }
            when(it.itemId){
                R.id.reportsFragment -> replaceFragment(reportsFragment)
            }
            when(it.itemId){
                R.id.weightFragment -> replaceFragment(weightFragment)
            }
            true
        }


        /*** Drawer layout items click listener */
        navView.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.homeNavDrawer -> {
                    replaceFragment(dashboardFragment)
                }
                R.id.workoutNavDrawer -> {
                    replaceFragment(workoutFragment)
                }
                R.id.reportsNavDrawer -> {
                    replaceFragment(reportsFragment)
                }
                R.id.weightNavDrawer -> {
                    replaceFragment(weightFragment)
                }
                R.id.profileNavDrawer -> {
                    replaceFragment(profileFragment)
                }
                R.id.aboutNavDrawer -> {
                    replaceFragment(aboutFragment)
                }
                R.id.feedbackNavDrawer -> {
                    replaceFragment(feedbackFragment)
                }
                R.id.settingsNavDrawer -> {
                    replaceFragment(settingsFragment)
                }

                R.id.signOutNavDrawer -> {
                    appSignOut()
                }

            }
            drawerLayout.closeDrawers()

            true
        }

//        populateUserDetails()

    }

//    private fun populateUserDetails(){
//
//        val userReference = rootReference.child("Users").child(firebaseAuth.currentUser!!.uid)
//
////        Log.d("sonusourav","uid = ${firebaseAuth.currentUser!!.uid}")
//
//        userReference.child("userDetails").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                val user = dataSnapshot.getValue(User::class.java)!!
//                Toast.makeText(this@HomeActivity, "hey ${user.userName}",Toast.LENGTH_SHORT).show()
//
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
//    }

    private fun replaceFragment(fragment : Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerViewHome, fragment)
            transaction.commit()
        }
    }

    /*** App sign out function */
    private fun appSignOut() {
        firebaseAuth.signOut()
        startActivity(Intent(this , SignInActivity::class.java))
        finish()
        checkUserSignOut()
    }

    /*** Checking user is Signed out or not */
    private fun checkUserSignOut() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()

        }
    }

    /*** Setting navigation up button in acton bar */
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.fragmentContainerViewHome)
//        return NavigationUI.navigateUp( navController , drawerLayout)
//
//    }

    /*** Setting option menu in action bar */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        //this.menu = menu!!
        return true
    }


    /*** Option menu items on click listener */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.aboutOptionsMenu -> {
                replaceFragment(aboutFragment)
            }
            R.id.shareOptionMenu -> {
                replaceFragment(shareFragment)
            }
            R.id.signOutOptionsMenu -> {
                appSignOut()
            }
        }
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
//        val navController = findNavController(R.id.fragmentContainerViewHome)


        return super.onOptionsItemSelected(item)

    }

}