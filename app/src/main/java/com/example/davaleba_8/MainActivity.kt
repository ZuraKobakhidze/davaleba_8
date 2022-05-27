package com.example.davaleba_8

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var noteSet: Set<String>
    lateinit var noteList: ArrayList<Note>

    lateinit var recyclerView: RecyclerView
    lateinit var addButton: Button
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        noteSet = setOf()
        noteList = arrayListOf<Note>()

        noteSet = sharedPreferences.getStringSet("NOTES", noteSet) as Set<String>
        noteSet.forEach {
            noteList.add(Note(it))
        }

        addButton = findViewById(R.id.addnewnotebtn)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)



        recyclerView.adapter = MyAdapter(noteList)

        editText = findViewById(R.id.addnotetxt)

        addButton.setOnClickListener {
            val noteText = editText.text.toString()
            noteSet.plusElement(noteText)
            noteList.add(Note(noteText))
            sharedPreferences.edit().putStringSet("NOTES", noteSet)
        }
    }
}
