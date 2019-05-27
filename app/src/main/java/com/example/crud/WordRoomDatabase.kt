package com.example.crud

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDAO

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDAO) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            //wordDao.deleteAll()

            var word = Word("Hello", "I am saw thandar!")
            wordDao.insert(word)
        }
    }

}
/*@Database(entities = arrayOf(Word::class), version = DATABASE_VERSION)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDAO

    companion object {
        const val DATABASE_VERSION = 1
        val DATABASE_NAME = "Note-Room"

        private var instance: WordRoomDatabase? = null

        fun getInstance(context: Context): WordRoomDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context, WordRoomDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

                return instance!!
            }
        }
    }*/


