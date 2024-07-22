package com.example.projectkp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projectkp.loginPage
import com.example.projectkp.databinding.ActivitySignupPageBinding
import com.google.firebase.auth.FirebaseAuth

class signupPage : AppCompatActivity() {
    lateinit var binding: ActivitySignupPageBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginredirect.setOnClickListener{
            val intent = Intent(this, loginPage::class.java)
            startActivity(intent)
        }
        binding.signupbutton.setOnClickListener {
            val email = binding.signupemailInput.text.toString()
            val password = binding.signuppasswordInput.text.toString()

            //Validasi email
            if (email.isEmpty()) {
                binding.signupemailInput.error = "Email Harus Diisi"
                binding.signupemailInput.requestFocus()
                return@setOnClickListener
            }
            //Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.signupemailInput.error = "Email Tidak Valid"
                binding.signupemailInput.requestFocus()
                return@setOnClickListener
            }
            //Validasi password
            if (password.isEmpty()) {
                binding.signuppasswordInput.error = "Password Harus Diisi"
                binding.signuppasswordInput.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.signuppasswordInput.error = "Password Minimal 6 Karakter"
                binding.signuppasswordInput.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password)
        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, loginPage::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}