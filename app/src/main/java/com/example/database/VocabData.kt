package com.example.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "VocabTable")
data class VocabData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") val ID: Int = 0,
    @ColumnInfo(name = "Type") val Type: String,
    @ColumnInfo(name = "Word") val Word: String,
    @ColumnInfo(name = "Definition") val Definition: String,
    @ColumnInfo(name = "Synonyms") val Synonyms: String,
    @ColumnInfo(name = "Antonyms") val Antonyms: String,
    @ColumnInfo(name = "Sentence") val Sentence: String,

    )

@Entity(tableName = "OwnListTable")
data class OwnVocabData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") val ID: Int = 0,
    @ColumnInfo(name = "Type") val Type: String,
    @ColumnInfo(name = "Word") val Word: String,
    @ColumnInfo(name = "Definition") val Definition: String,
    @ColumnInfo(name = "Synonyms") val Synonyms: String,
    @ColumnInfo(name = "Antonyms") val Antonyms: String,
    @ColumnInfo(name = "Sentence") val Sentence: String,

    )