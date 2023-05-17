package com.example.notes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView

import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Models.Note
import com.example.notes.R
import kotlin.random.Random

class NotesAdapter(private val context: Context,val listener:NotesClickListener) :RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    private var notesList=ArrayList<Note>()
    private var fullList=ArrayList<Note>()

    inner class  NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val notes_layout=itemView.findViewById<CardView>(R.id.card_layout)
        val title= itemView.findViewById<TextView>(R.id.tv_title)
        val note= itemView.findViewById<TextView>(R.id.tv_note)
        val date=itemView.findViewById<TextView>(R.id.tv_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun randomColor():Int{
        val list=ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        list.add(R.color.NoteColor6)

        val seed= System.currentTimeMillis().toInt()
        val randomIndex= Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote= notesList[position]
        holder.title.text=currentNote.title.toString()
        holder.title.isSelected=true
        holder.note.text=currentNote.note.toString()
        holder.date.text=currentNote.date.toString()
        holder.date.isSelected=true
        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))
        holder.notes_layout.setOnClickListener{
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener {
            listener.onlongItemClicked(notesList[holder.adapterPosition],holder.notes_layout)
            true
        }
    }

    interface NotesClickListener{
        fun onItemClicked(note:Note)
        fun onlongItemClicked(note:Note,cardView: CardView)
    }

    fun updateList(newList:List<Note>){
        fullList.clear()
        fullList.addAll(newList)
        notesList.clear()
        notesList.addAll(newList)
        notifyDataSetChanged()

    }

    fun filterList(search:String){
        notesList.clear()
        for(item in fullList){
            if(item.title?.lowercase()?.contains(search)==true|| item.note?.lowercase()?.contains(search)==true){
                notesList.add(item)
            }
        }
        notifyDataSetChanged()
    }

}