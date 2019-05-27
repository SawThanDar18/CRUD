package com.example.crud

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecyclerView_Item : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_item)

        var update_btn = findViewById<Button>(R.id.update_btn)
        update_btn.setOnClickListener {
            Toast.makeText(applicationContext, "blah", Toast.LENGTH_LONG).show()
        }
    }
}