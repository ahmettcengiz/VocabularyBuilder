package com.example.vocabularybuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.database.VocabData
import com.example.database.VocabViewModel
import kotlinx.android.synthetic.main.activity_quiz.*


class Quiz : AppCompatActivity() {
    private lateinit var vocabViewModel: VocabViewModel

    var list : ArrayList<VocabData> = ArrayList()
    var newList : ArrayList<VocabData> = ArrayList()
    var temp=0
    var correct : VocabData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        var optionTemp = intent.getStringExtra("category")!!
        Log.e("asdas",optionTemp)

        vocabViewModel = ViewModelProvider(this)[VocabViewModel::class.java]
        vocabViewModel.allVocabs.observe(this, { vocabs ->
                Log.e("asdk",vocabs.size.toString())
                for (k in 0..vocabs.size-1){
                    val a : VocabData = vocabs.get(k)
                    Log.e("asd",a.Word)
                    if (a.Type==optionTemp)
                    {
                        list!!.add(a)
                    }
                }

            newList = createQuiz(list)
            correct  = correctValue(newList)
            designFrame(newList,correct!!)


            })






        button.setOnClickListener {
            if(correct!!.Word==button.text){
                list = createList(optionTemp)
                temp=0
                newList = createQuiz(list)
                correct  = correctValue(newList)
                designFrame(newList,correct!!)

            }
            else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }
        }
        button2.setOnClickListener {
            if(correct!!.Word==button2.text){
                list = createList(optionTemp)
                temp=0
                newList = createQuiz(list)
                correct  = correctValue(newList)
                designFrame(newList,correct!!)

            }
            else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }
        }
        button3.setOnClickListener {
            if(correct!!.Word==button3.text){
                list = createList(optionTemp)
                temp=0
                newList = createQuiz(list)
                correct  = correctValue(newList)
                designFrame(newList,correct!!)

            }
            else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }
        }
        button4.setOnClickListener {
            if(correct!!.Word==button4.text){
                list = createList(optionTemp)
                temp=0
                newList = createQuiz(list)
                correct  = correctValue(newList)
                designFrame(newList,correct!!)

            }
            else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }
        }




    }
    fun createQuiz(list:ArrayList<VocabData>) : java.util.ArrayList<VocabData> {
        val List = list.shuffled()
        val newList = java.util.ArrayList<VocabData>()
        for (k in 0..3){
            newList.add(List[k])
        }
        return newList
    }
    fun correctValue(list:ArrayList<VocabData>) :VocabData{
        val random = (0..list.size-1).random()
        val correct = list.get(random)

        return correct
    }

    fun designFrame(list:ArrayList<VocabData>,correct:VocabData){

        textView.setText(correct.Definition)

        val x = (0..list.size-1).random()
        when (x) {
            0 ->{
                button.setText(correct.Word)
                list.remove(correct)
                button2.setText(list[0].Word)
                button3.setText(list[1].Word)
                button4.setText(list[2].Word)

            }
            1 ->{
                button2.setText(correct.Word)
                list.remove(correct)
                button.setText(list[0].Word)
                button3.setText(list[1].Word)
                button4.setText(list[2].Word)
            }
            2 ->{
                button3.setText(correct.Word)
                list.remove(correct)
                button.setText(list[0].Word)
                button2.setText(list[1].Word)
                button4.setText(list[2].Word)
            }
            3 ->{
                button4.setText(correct.Word)
                list.remove(correct)
                button.setText(list[0].Word)
                button2.setText(list[1].Word)
                button3.setText(list[2].Word)

            }

        }


    }
    fun createList(optionTemp : String) : ArrayList<VocabData>{
        val list : ArrayList<VocabData> = ArrayList()
        vocabViewModel = ViewModelProvider(this)[VocabViewModel::class.java]
        vocabViewModel.allVocabs.observe(this, { vocabs ->
            Log.e("asdk",vocabs.size.toString())
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