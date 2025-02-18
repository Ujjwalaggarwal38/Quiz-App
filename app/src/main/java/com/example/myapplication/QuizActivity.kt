package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityQuizBinding
import com.google.android.material.color.utilities.Score
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private val binding: ActivityQuizBinding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }
    lateinit var list:ArrayList<QuestionModal>
    private var count:Int=0
    private var score:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        list=ArrayList<QuestionModal>()
        Firebase.firestore.collection("quiz").get().addOnSuccessListener{
            list.clear()
            for (i in it.documents){
                var questionModal= i.toObject(QuestionModal::class.java)
                if (questionModal != null) {
                    list.add(questionModal)
                }

            }
            binding.question.setText(list.get(0).question)
            binding.Op1.setText(list.get(0).option1)
            binding.Op2.setText(list.get(0).option2)
            binding.Op3.setText(list.get(0).option3)
            binding.Op4.setText(list.get(0).option4)

        }
//        list.add(
//            QuestionModal(
//                "Who is the Pm of India",
//                "Narender Modi",
//                "Rahul Gandhi",
//                "Nirmala",
//                "None",
//                "Narender Modi"))
//        list.add(
//            QuestionModal(
//                "Who is the Pm of India",
//                "Narender Modi",
//                "Rahul Gandhi",
//                "Nirmala",
//                "None",
//                "Narender Modi"))
//        list.add(
//            QuestionModal(
//                "Who is the Pm of India",
//                "Narender Modi",
//                "Rahul Gandhi",
//                "Nirmala",
//                "None",
//                "Narender Modi"))
//        list.add(
//            QuestionModal(
//                "Who is the Pm of India",
//                "Narender Modi",
//                "Rahul Gandhi",
//                "Nirmala",
//                "None",
//                "Narender Modi"))
//        list.add(
//            QuestionModal(
//                "Who is the Pm of India",
//                "Narender Modi",
//                "Rahul Gandhi",
//                "Nirmala",
//                "None",
//                "Narender Modi"))



        binding.Op1.setOnClickListener {
            nextData(binding.Op1.text.toString())
        }
        binding.Op2.setOnClickListener {
            nextData(binding.Op2.text.toString())
        }
        binding.Op3.setOnClickListener {
            nextData(binding.Op3.text.toString())
        }
        binding.Op4.setOnClickListener {
            nextData(binding.Op4.text.toString())
        }
    }

    private fun nextData(i: String) {
        if(count<list.size){
            if(list.get(count).ans.equals(i)){
                score++
            }
        }

            count++
        if(count>=list.size){
            val intent = Intent(this,ScoreActivity::class.java)
            intent.putExtra("Score",score)
            startActivity(intent)
            finish()
        }
        else{
            binding.question.setText(list.get(count).question)
            binding.Op1.setText(list.get(count).option1)
            binding.Op2.setText(list.get(count).option2)
            binding.Op3.setText(list.get(count).option3)
            binding.Op4.setText(list.get(count).option4)
        }

    }
}