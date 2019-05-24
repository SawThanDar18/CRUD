package com.example.crud

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.StringBuilder

@Entity(tableName = "note_table")
data class Word(@PrimaryKey @ColumnInfo(name = "title") var title : String, @ColumnInfo(name = "notes") var notes : String)

    /*constructor(){}

    override fun toString() : String{
        return StringBuilder(title)
            .append("\n")
            .append(notes)
            .toString()*/





