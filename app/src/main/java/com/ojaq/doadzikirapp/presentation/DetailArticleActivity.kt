package com.ojaq.doadzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ojaq.doadzikirapp.R
import com.ojaq.doadzikirapp.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {
    private var _binding : ActivityDetailArticleBinding? = null
    private val binding get() = _binding as ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        val titleArticle = intent.getStringExtra("title")
        val contentArticle = intent.getStringExtra("content")
        val imageArticle = intent.getIntExtra("image", 1)

        binding.apply {
            tvDetailTitle.text = titleArticle
            tvDetailContent.text = contentArticle
            detailImgArticle.setImageResource(imageArticle)
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