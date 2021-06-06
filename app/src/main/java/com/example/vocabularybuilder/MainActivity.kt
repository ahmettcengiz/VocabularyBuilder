package com.example.vocabularybuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.database.OwnVocabData
import com.example.database.VocabData
import com.example.database.VocabViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var vocabViewModel: VocabViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        var randomObject : OwnVocabData? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vocabViewModel = ViewModelProvider(this)[VocabViewModel::class.java]
        vocabViewModel.ownVocabs.observe(this, { vocabs ->
                var random = (0..vocabs.size-1).random()
                randomObject = vocabs.get(random)
                startService(Intent(this, Notification::class.java).putExtra("word",randomObject!!.Word))

        })

        vocabViewModel.ownVocabs.observe(this, { vocabs ->
            Log.e("asd",vocabs.size.toString())
            for (k in 0..vocabs.size-1){
                val a : OwnVocabData = vocabs.get(k)
                Log.e("asd",a.Word)
                if (a.Type=="Idiom")
                {
                    Log.e("asdtt",a.Definition)
                }
            }
        })




        random.setOnClickListener {
            val intent = Intent(this, Random::class.java)
            startActivity(intent)
        }
        quiz.setOnClickListener {
            val intent = Intent(this, QuizCategory::class.java)
            startActivity(intent)
        }
        addWord.setOnClickListener {
            val intent = Intent(this, AddWord::class.java)
            startActivity(intent)
        }










    }
}