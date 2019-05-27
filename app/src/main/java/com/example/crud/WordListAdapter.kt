package com.example.crud

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words

    var context = context

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val wordItemView: TextView = itemView.findViewById(R.id.text_title)
        val wordItemView2 : TextView = itemView.findViewById(R.id.text_note)


        init {
            itemView.setOnClickListener(this)
        }
        var customItemClickListener : CustomItemClickListener?= null

        fun setOnCustomItemClickListener(itemClickListener: CustomItemClickListener){
            this.customItemClickListener = itemClickListener
        }

        override fun onClick(v: View?) {
            this.customItemClickListener!!.onCustomItemClickListener(v!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {

        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        val current = words[position]
        holder.wordItemView.text = current.title
        holder.wordItemView2.text = current.notes
        holder.setOnCustomItemClickListener(object : CustomItemClickListener{
            override fun onCustomItemClickListener(view: View, position: Int) {
                Toast.makeText(context, holder.wordItemView.text, Toast.LENGTH_LONG).show()
            }
        })
    }
    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}