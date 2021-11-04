package com.byfreakdevs.fitme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.byfreakdevs.fitme.R
import com.byfreakdevs.fitme.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var binding : ActivityForgetPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSendEmailForgetPassword.setOnClickListener {
            val email = binding.etEmailForgetPassword.text.toString().trim()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmailForgetPassword.error = "Invalid email format "
            }
            else{
                firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Email send successfully to reset password" , Toast.LENGTH_SHORT).show()
                        }
                        finish()
                    }
            }
        }
        binding.tvReturnLoginForgetPassword.setOnClickListener {
            startActivity(Intent(this , SignInActivity :: class.java))
        }
    }
}