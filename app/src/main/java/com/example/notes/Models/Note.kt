package com.example.notes.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo val title:String?,
    @ColumnInfo val note:String?,
    @ColumnInfo val date:String?,
):Serializable
