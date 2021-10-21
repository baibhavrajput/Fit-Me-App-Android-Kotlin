package com.byfreakdevs.fitme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private lateinit var firebaseAuth : FirebaseAuth
    private var email = ""
    private var password = ""

//    lateinit var database: RoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //database = Room.databaseBuilder(applicationContext , NutritionDatabase::class.java , "nutritionDB").build()
//        database.nutritionDao()

        /** Initialize Firebase Auth */
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        /** Create account button on SignUp page */
        binding.tvCreateAccountSignIn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        /** Sign in button on SignIn page*/
        binding.btnSignIn.setOnClickListener {
            validateData()
        }

    }
    /** Validate users SignIn data*/
    private fun validateData() {
        email = binding.etEmailSignIn.text.toString().trim()
        password = binding.etPasswordSignIn.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmailSignIn.error = "Invalid email format "
        }
        else if(TextUtils.isEmpty(password)){
            binding.etPasswordSignIn.error = "Please enter password"
        }
        else{
            firebaseSignIn()
        }

    }
    /** Firebase SignIn*/
    private fun firebaseSignIn() {
        firebaseAuth.signInWithEmailAndPassword(email , password )
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email

                Toast.makeText(this, "Sign in successful as $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()

            }
            .addOnFailureListener {
                Toast.makeText(this, "Sign in failed ", Toast.LENGTH_SHORT).show()
            }
    }
    /**Check if user is already registered */
    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
//        else{
//            startActivity(Intent(this , SignUpActivity::class.java))
//            finish()
//        }
    }


}