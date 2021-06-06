package com.example.vocabularybuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.database.OwnVocabData
import com.example.database.VocabData
import com.example.database.VocabViewModel
import kotlinx.android.synthetic.main.activity_add_word.*

class AddWord : AppCompatActivity() {
    private lateinit var vocabViewModel: VocabViewModel
    var type = "a"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)
        vocabViewModel = ViewModelProvider(this)[VocabViewModel::class.java]
        val adapter = ArrayAdapter(baseContext,android.R.layout.simple_spinner_item , listOf("Verb", "Adverb", "Adjective","Idiom"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.adapter=adapter
        spinnerType.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                type="Verb"
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                type = adapter.getItem(position).toString()
            }
        }


        createButton.setOnClickListener {
            val word = editTextWord.text.toString()
            val definition = editTextDefinition.text.toString()
            val synonyms = editTextSynonyms.text.toString()
            val antonyms = editTextAntonyms.text.toString()
            val sentence = editTextSentence.text.toString()

            val temp  = OwnVocabData(0,type,word,definition,synonyms,antonyms,sentence)
            vocabViewModel.insertOwnVocab(temp)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)



        }




    }
}