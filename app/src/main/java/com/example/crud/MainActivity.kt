package com.example.crud

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_new_word.view.*
import kotlinx.android.synthetic.main.recyclerview_item.*


class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var wordViewModel: WordViewModel
    private lateinit var toolBar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WordListAdapter
    //private lateinit var update : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolBar)

        recyclerView = findViewById(R.id.recyclerview)
        adapter = WordListAdapter(applicationContext)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val word = Word(data.getStringExtra(NewWordActivity.TITLE), data.getStringExtra(NewWordActivity.NOTES))
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }

    }

   /* override fun onCreateContextMenu(menu: ContextMenu, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {

        super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        menu.setHeaderTitle("Select Action :")
        menu.add(Menu.NONE, 0, Menu.NONE, "Update")
        menu.add(Menu.NONE, 1, Menu.NONE, "Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return super.onContextItemSelected(item)
        //val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        //val user = recyclerView(info.position)
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val user = adapter(info.position)

        when(item.itemId)
        {
            0
            ->
            {
                val editTitle = EditText(this)
                editTitle.setText(user)
                editTitle.hint = "Enter title"

                AlertDialog.Builder(this)
                    .setTitle("Edit")
                    .setMessage("Edit title")
                    .setView(editTitle)
                    .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener{dialog, which ->
                     if(TextUtils.isEmpty(editTitle.text.toString()))
                       return@OnClickListener
                        else
                     {
                         user.edit_word = editTitle.text.toString()
                     }
                    })
            }*/
        }

/* private lateinit var adapter : ArrayAdapter<*>
 var noteList : MutableList<Word> = ArrayList()

 private var compositeDisposable : CompositeDisposable?=null
 private var wordRepository : WordRepository? = null

 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)

     compositeDisposable = CompositeDisposable()
     adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,noteList)

     registerForContextMenu(recyclerview)
     recyclerview.adapter = adapter

 }*/

