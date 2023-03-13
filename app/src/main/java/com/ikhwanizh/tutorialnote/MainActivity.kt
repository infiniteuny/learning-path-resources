package com.ikhwanizh.tutorialnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikhwanizh.tutorialnote.data.NoteDb
import com.ikhwanizh.tutorialnote.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreate.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val NoteDB = NoteDb(this@MainActivity).noteDao().getNotes()
            binding.rvNote.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = NoteAdapter().apply {
                    setData(NoteDB)
                }
            }
        }
    }
}