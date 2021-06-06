package com.example.database

import androidx.lifecycle.LiveData

class VocabRepository(private val VocabDao: VocabDao) {

    val allVocabs: LiveData<List<VocabData>> = VocabDao.findAll()

    val ownVocabs: LiveData<List<OwnVocabData>> = VocabDao.findAllOwn()

    fun insertVocab(VocabData: VocabData) {
        VocabDao.insert(VocabData)
    }
    fun insertOwnVocab(ownVocabData: OwnVocabData) {
        VocabDao.insertOwn(ownVocabData)
    }

    fun deleteVocab(ownVocabData: OwnVocabData) {
        VocabDao.delete(ownVocabData)
    }
}