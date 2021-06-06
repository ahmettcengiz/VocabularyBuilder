package com.example.vocabularybuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quiz_category.*

class QuizCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_category)

        verbButton.setOnClickListener {
            startActivity("Verb")
        }
        adverbButton.setOnClickListener {
            startActivity("Adverb")
        }
        adjectivesButton.setOnClickListener {
            startActivity("Adjective")
        }
        idiomsButton.setOnClickListener {
            startActivity("Idioms")
        }





    }
    fun startActivity(category:String){
        val intent = Intent(this, Quiz::class.java)
        intent.putExtra("category",category.toString())
        startActivity(intent)
    }
}