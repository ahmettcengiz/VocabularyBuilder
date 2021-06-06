package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.vocabularybuilder.R
import java.io.InputStream


const val DB_VERSION = 1

@Database(entities = [VocabData::class,OwnVocabData::class], version = DB_VERSION, exportSchema = true)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun VocabDao():VocabDao

    companion object {
        private var INSTANCE: LocalDatabase? = null
        fun getAppDatabase(context: Context): LocalDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<LocalDatabase>(
                    context.applicationContext, LocalDatabase::class.java, "VocabDB"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Thread(Runnable { INSTANCE?.let { prepopulateDb(context, it) } }).start()

                        }
                    })
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }

        fun prepopulateDb(context: Context, db: LocalDatabase) {
            val inputStream: InputStream = context.resources.openRawResource(R.raw.file)
            val lineList = mutableListOf<String>()

            inputStream.bufferedReader().forEachLine { lineList.add(it) }
            lineList.forEach{
                val yourArray: List<String> = it.split("!")
                val a = VocabData(0,yourArray[0],yourArray[1],yourArray[2],yourArray[3],yourArray[4],yourArray[5])
                db.VocabDao().insert(a)
            }

        }

    }

}
