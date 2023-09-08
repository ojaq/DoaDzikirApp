package com.ojaq.doadzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojaq.doadzikirapp.R
import com.ojaq.doadzikirapp.adapter.DoaDzikirAdapter
import com.ojaq.doadzikirapp.databinding.ActivityDzikirSetiapSaatBinding
import com.ojaq.doadzikirapp.model.DataDoaDzikir

class DzikirSetiapSaatActivity : AppCompatActivity() {
    private var _binding: ActivityDzikirSetiapSaatBinding? = null
    private val binding get() = _binding as ActivityDzikirSetiapSaatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //show navigation button home
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        //initialize property of _binding with layoutInflater to get the layout
        _binding = ActivityDzikirSetiapSaatBinding.inflate(layoutInflater)
        //replace argument of set ContentView using viewBinding
        //this is for connecting the view using view binding
        setContentView(binding.root)

        //set RecyclerView
        val mAdapter = DoaDzikirAdapter()
        mAdapter.setData(DataDoaDzikir.DataDoaDzikir.listDzikir)
        binding.rvDzikir.adapter = mAdapter
        //layoutManager is a class to set our structure of RecyclerView to display dataset
        binding.rvDzikir.layoutManager = LinearLayoutManager(this)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}