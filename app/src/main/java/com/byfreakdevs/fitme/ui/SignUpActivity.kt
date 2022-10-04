package com.byfreakdevs.fitme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.databinding.ActivitySignUpBinding
import com.byfreakdevs.fitme.utlis.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private var email = ""
    private var password = ""
    private var name = ""
    private var userNameId = ""
    private var confirmPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Initialize Firebase Auth */
        firebaseAuth = FirebaseAuth.getInstance()

        /** Already have a account button on SignIn page */
        binding.tvAreadyAccountSignUp.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        /** Sign up button on SignUp page */
        binding.btnSignUp.setOnClickListener {
            validateData()
        }
    }

    /** Validating users SignUp details */
    private fun validateData() {
        email = binding.etEmailSignUp.text.toString().trim()
        password = binding.etPasswordSignUp.text.toString().trim()
        confirmPassword = binding.etConfirmPasswordSignUp.text.toString().trim()
        name = binding.etNameSignUp.text.toString().trim()
        userNameId = binding.etUserNameSignUp.text.toString().trim()

        if (TextUtils.isEmpty(name)) {
            binding.etNameSignUp.error = "Please enter name"
        } else if (TextUtils.isEmpty(userNameId)) {
            binding.etUserNameSignUp.error = "Please enter username"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmailSignUp.error = "Invalid email format "
        } else if (TextUtils.isEmpty(password)) {
            binding.etPasswordSignUp.error = "Please enter password"
        } else if (TextUtils.isEmpty(confirmPassword)) {
            binding.etConfirmPasswordSignUp.error = "Please confirm password"
        } else if (password.length < 6) {
            binding.etPasswordSignUp.error = "Password must at least be 8 characters long"
        } else if (confirmPassword != password) {
            binding.etConfirmPasswordSignUp.error = "Please enter same password"
        } else {
            firebaseSignUp()
        }
    }

    /** Signing in user user firebase */
    private fun firebaseSignUp() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                firebaseDatabase()

                val firebaseUser = firebaseAuth.currentUser
                val firebaseEmail = firebaseUser!!.email

                Toast.makeText(
                    this,
                    "account created with email $firebaseEmail",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("userNameId", userNameId)
                startActivity(intent)
                finish()

            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }

    /** Putting Users SignUp details on firebase realtime database */
    private fun firebaseDatabase() {

        val userFirebaseId = firebaseAuth.currentUser?.uid
        val userEmail = firebaseAuth.currentUser!!.email
        val userName = binding.etNameSignUp.text.toString()
        val userId = binding.etUserNameSignUp.text.toString()

        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = User(userName, userId, userEmail, userFirebaseId)
        database.child(userFirebaseId!!).child("userDetails").setValue(user)

    }
}