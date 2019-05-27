package com.example.crud

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_new_word.view.*
import kotlinx.android.synthetic.main.recyclerview_item.*
import kotlin.concurrent.thread

class NewWordActivity : AppCompatActivity(){

    private lateinit var editTitleView: EditText
    private lateinit var editNoteView : EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editTitleView = findViewById(R.id.edit_word)
        editNoteView = findViewById(R.id.edit_note)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTitleView.text) || TextUtils.isEmpty(editNoteView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editTitleView.text.toString()
                val note = editNoteView.text.toString()
                replyIntent.putExtra(TITLE, word)
                replyIntent.putExtra(NOTES, note)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        val TITLE = "title"
        val NOTES = "notes"
    }
}