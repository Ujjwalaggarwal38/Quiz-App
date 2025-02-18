package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
       binding.button.setOnClickListener {
           Firebase.auth.createUserWithEmailAndPassword(
               binding.email.editText?.text.toString(),
               binding.password.editText?.text.toString()
           ).addOnCompleteListener{
               if(it.isSuccessful){
                   val intent = Intent(this,QuizActivity::class.java)
                   startActivity(intent)
                   Toast.makeText(this,"User Created", Toast.LENGTH_SHORT).show()
               }
               else{
                   Toast.makeText(this,"User is not Created", Toast.LENGTH_LONG).show()
               }
           }
       }
    }
}