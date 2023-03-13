package com.ikhwanizh.tutorialnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.ikhwanizh.tutorialnote.data.Note
import com.ikhwanizh.tutorialnote.data.NoteDb
import com.ikhwanizh.tutorialnote.databinding.ActivityEditBinding
import com.ikhwanizh.tutorialnote.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreateOrEdit.setOnClickListener { addNote() }
    }

    private fun addNote() {
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()

        lifecycleScope.launch {
            val note = Note(title, content)
            NoteDb(this@EditActivity).noteDao().addNote(note)
            finish()
        }
    }
}