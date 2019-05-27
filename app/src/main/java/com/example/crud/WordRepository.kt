package com.example.crud

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDAO){

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    fun deleteAll(){
        wordDao.deleteAll()
    }

   /* override val allNotes: LiveData<List<Word>>
        get() = dataSource.allNotes

    override fun insertNote(vararg words: Word) {
        return dataSource.insertNote(*words)
    }

    override fun updateNote(vararg words: Word) {
        return dataSource.updateNote(*words)
    }

    override fun deleteAll() {
        return dataSource.deleteAll()
    }
*/
}