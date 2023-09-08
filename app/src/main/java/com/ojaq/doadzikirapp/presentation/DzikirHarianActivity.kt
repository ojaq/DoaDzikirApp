package com.ojaq.doadzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojaq.doadzikirapp.adapter.DoaDzikirAdapter
import com.ojaq.doadzikirapp.R
import com.ojaq.doadzikirapp.databinding.ActivityDzikirHarianBinding
import com.ojaq.doadzikirapp.model.DoaDzikirItem

class DzikirHarianActivity : AppCompatActivity() {
    private var _binding: ActivityDzikirHarianBinding? = null
    private val binding get() = _binding as ActivityDzikirHarianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //show navigation button home
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        _binding = ActivityDzikirHarianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        providingDzikirDatas()
        initView()
    }

    private fun initView() {
        val mAdapter = DoaDzikirAdapter()
        mAdapter.setData(providingDzikirDatas())
        binding.rvDzikirHarian.adapter = mAdapter
        binding.rvDzikirHarian.layoutManager = LinearLayoutManager(this)
    }

    private fun providingDzikirDatas(): List<DoaDzikirItem> {
        //data set of dzikir harian is located in strings.xml
        //we need to get string array from strings.xml to put into variable
        //resources is a variable frooom AppCompat that gets acces to Resource Directory
        //from resources we can call Resource Directory like drawable, layout, values (strings, theme, color)
        //variable titleDzikir contains datas String-Array arr_dzikir_doa_harian
        val titleDzikir = resources.getStringArray(R.array.arr_dzikir_doa_harian)
        val arabicDzikir = resources.getStringArray(R.array.arr_lafaz_dzikir_doa_harian)
        val translateDzikir = resources.getStringArray(R.array.arr_terjemah_dzikir_doa_harian)

        val list = arrayListOf<DoaDzikirItem>()
        for (data in titleDzikir.indices) {
            val dzikir = DoaDzikirItem(
                titleDzikir[data],
                arabicDzikir[data],
                translateDzikir[data]
            )
            list.add(dzikir)
        }
        return list
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