package com.ojaq.doadzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojaq.doadzikirapp.R
import com.ojaq.doadzikirapp.adapter.DoaDzikirAdapter
import com.ojaq.doadzikirapp.databinding.ActivityQouliyahShalatBinding
import com.ojaq.doadzikirapp.model.DataDoaDzikir.DataDoaDzikir.listQouliyah

class QouliyahShalatActivity : AppCompatActivity() {
    //implement viewBinding feature
    //viewBinding is a feature that makes it easier to write code that interacts with views
    //viewBinding comes to replace findViewById
    private var _binding: ActivityQouliyahShalatBinding? = null
    private val binding get() = _binding as ActivityQouliyahShalatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        show navigation button home
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        //initialize property of _binding with layoutInflater to get the layout
        _binding = ActivityQouliyahShalatBinding.inflate(layoutInflater)
        //replace argument of set ContentView using viewBinding
        //this is for connecting the view using view binding
        setContentView(binding.root)

        //set RecyclerView
        val mAdapter = DoaDzikirAdapter()
        mAdapter.setData(listQouliyah)
        binding.rvQauliyahShalat.adapter = mAdapter
        //layoutManager is a class to set our structure of RecyclerView to display dataset
        binding.rvQauliyahShalat.layoutManager = LinearLayoutManager(this)
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