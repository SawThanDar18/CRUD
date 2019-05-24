package com.example.crud

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Word(@PrimaryKey @ColumnInfo(name = "title") val title : String,
                @Nullable @ColumnInfo(name = "notes") val notes : String)