package com.example.crud

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDAO {

    @Query("SELECT * from note_table ORDER BY title ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM note_table")
    fun deleteAll()

    //@get:Query("SELECT * from note_table")
    //val allNotes : LiveData<List<Word>>

    /*@Insert
    fun insertNote(vararg words: Word)

    @Update
    fun updateNote(vararg words: Word)

    @Delete
    fun deleteNote(words: Word)*/

    //new
    /*val allNotes : LiveData<List<Word>>
    fun updateNote(vararg words: Word)
    fun deleteNote(word: Word)*/

}