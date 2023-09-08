package com.ojaq.doadzikirapp.presentation.pagipetang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojaq.doadzikirapp.R
import com.ojaq.doadzikirapp.adapter.DoaDzikirAdapter
import com.ojaq.doadzikirapp.databinding.ActivityPetangBinding
import com.ojaq.doadzikirapp.model.DataDoaDzikir

class PetangActivity : AppCompatActivity() {
    private var _binding: ActivityPetangBinding? = null
    private val binding get() = _binding as ActivityPetangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //change title text in ActionBar
        title = "Dzikir Pagi"
        //show navigation button home
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        _binding = ActivityPetangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDzikirPetang.apply {
            val mAdapter = DoaDzikirAdapter()
            mAdapter.setData(DataDoaDzikir.DataDoaDzikir.listDzikirPetang)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@PetangActivity)
        }
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