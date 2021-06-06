package com.example.vocabularybuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.database.VocabData
import com.example.database.VocabViewModel
import kotlinx.android.synthetic.main.activity_random.*

class Random : AppCompatActivity() {
    private lateinit var vocabViewModel: VocabViewModel
    var optionTemp : String = "Verb"
    var list : ArrayList<VocabData> = ArrayList()
    var temp=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        list = createList(optionTemp)
        Log.e("asd",list.size.toString())



        verbsOption.setOnClickListener {
            optionTemp = "Verb"
            list = createList(optionTemp)
            temp=0
            designFrame(list,temp)
        }
        adverbsOption.setOnClickListener {
            optionTemp = "Adverb"
            temp=0
            list = createList(optionTemp)
            designFrame(list,temp)

        }
        adjectivesOption.setOnClickListener {
            optionTemp = "Adjective"
            list = createList(optionTemp)
            temp=0
            designFrame(list,temp)
        }
        idiomsOption.setOnClickListener {
            optionTemp = "Idiom"
            list = createList(optionTemp)
            temp=0
            designFrame(list,temp)
        }

        back.setOnClickListener {
            temp--
            if (temp<0){
                temp=0
            }else{
                designFrame(list!!,temp)
            }

        }
        next.setOnClickListener {
            temp++
            if (temp>9){
                temp=9
            }else{
                designFrame(list!!,temp)
            }
        }


    }

    fun designFrame(list:ArrayList<VocabData>, i:Int){
        textType.setText(list!!.get(i).Type)
        textWord.setText(list!!.get(i).Word)
        textDefinition.setText(list!!.get(i).Definition)
        textSynonmys.setText(list!!.get(i).Synonyms)
        textAntonmys.setText(list!!.get(i).Antonyms)
        textSentence.setText(list!!.get(i).Sentence)
    }


    fun createList(optionTemp : String,) : ArrayList<VocabData>{
        val list : ArrayList<VocabData> = ArrayList()
        vocabViewModel = ViewModelProvider(this)[VocabViewModel::class.java]
        vocabViewModel.allVocabs.observe(this, { vocabs ->
            Log.e("asd",vocabs.size.toString())
            for (k in 0..vocabs.size-1){
                val a : VocabData = vocabs.get(k)
                Log.e("asd",a.Word)
                if (a.Type==optionTemp)
                {
                    list!!.add(a)
                }
            }
        })

        return list
    }
}