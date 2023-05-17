package com.example.notes.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.Models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)
    @Delete
    suspend fun delete(note:Note)

    @Query("Select * From notes_table order by id asc")
    fun getAllNotes():LiveData<List<Note>>

    @Query("Update notes_table set title= :title,note= :note where id= :id")
    suspend fun update(id:Int?,title:String?,note:String?){

    }
}