package com.example.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class VocabViewModel(application: Application): AndroidViewModel(application) {

    val allVocabs: LiveData<List<VocabData>>
    val ownVocabs: LiveData<List<OwnVocabData>>
    private val repository: VocabRepository

    init{
        val vocabDao = LocalDatabase.getAppDatabase(application)?.VocabDao()
        repository = VocabRepository(vocabDao!!)
        allVocabs = repository.allVocabs
        ownVocabs = repository.ownVocabs
    }

    fun insertVocab(VocabData: VocabData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertVocab(VocabData)
        }
    }
    fun insertOwnVocab(ownVocabData: OwnVocabData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertOwnVocab(ownVocabData)
        }
    }

    fun deleteVocab(ownVocabData: OwnVocabData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteVocab(ownVocabData)
        }
    }

}