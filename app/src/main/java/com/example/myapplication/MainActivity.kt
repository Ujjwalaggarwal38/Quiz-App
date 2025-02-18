package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({
            if(Firebase.auth.currentUser!=null){
                val intent = Intent(this,QuizActivity::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this,LoginActivity::class.java)
                intent.putExtra("MODE","Signup")
                startActivity(intent)
            }
        },2000)
    }
}