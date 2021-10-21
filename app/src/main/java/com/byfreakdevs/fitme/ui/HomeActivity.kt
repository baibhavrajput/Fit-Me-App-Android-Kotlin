package com.byfreakdevs.fitme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.byfreakdevs.fitme.Communicator
import com.byfreakdevs.fitme.fragmentsHomeActivity.ReportsFragment
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.databinding.ActivityHome2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity(), Communicator {

    private lateinit var binding: ActivityHome2Binding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var firebaseAuth : FirebaseAuth
    private val rootReference = Firebase.database.reference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        /** Initialize Firebase Auth */
        firebaseAuth = FirebaseAuth.getInstance()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationMenuHome)
        val navController = findNavController(R.id.fragmentContainerViewHome)
        val navView = findViewById<NavigationView>(R.id.navView)

        bottomNavigationView.setupWithNavController(navController)

        /*** Setting up drawer layout in action bar */
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        /*** Drawer layout items click listener */
        navView.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.signOutNavDrawer -> {
                    appSignOut()
                }
            }
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
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentContainerViewHome)
        return NavigationUI.navigateUp( navController , drawerLayout)

    }

    /*** Setting option menu in action bar */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

//        this.menu = menu!!
        return true
    }


    /*** Option menu items on click listener */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navController = findNavController(R.id.fragmentContainerViewHome)

        when (item.itemId) {
            R.id.aboutOptionsMenu -> {
                navController!!.navigate(R.id.aboutFragment)
            }
            R.id.signOutOptionsMenu -> {
                appSignOut()
            }
        }

        return super.onOptionsItemSelected(item)

    }

    override fun passDataCom(totalCarbs: Double) {
        val bundle = Bundle()
        bundle.putDouble("totalCarbs" , totalCarbs)

        val transaction = this.supportFragmentManager.beginTransaction()
        val reportsFragment = ReportsFragment()

        transaction.replace(R.id.fragmentContainerViewHome , reportsFragment)
        transaction.commit()

    }


}

