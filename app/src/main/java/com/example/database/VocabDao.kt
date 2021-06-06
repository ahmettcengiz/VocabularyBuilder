package com.example.database

import androidx.lifecycle.LiveData
import androidx.room.*


    @Dao
    interface VocabDao {
        @Query("SELECT * FROM VocabTable")
        fun findAll(): LiveData<List<VocabData>>

        @Query("SELECT * FROM OwnListTable")
        fun findAllOwn(): LiveData<List<OwnVocabData>>

        @Delete
        fun delete(OwnVocabData: OwnVocabData)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(VocabData: VocabData)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertOwn(OwnVocabData: OwnVocabData)


}