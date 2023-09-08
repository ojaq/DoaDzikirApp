package com.ojaq.doadzikirapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.card.MaterialCardView
import com.ojaq.doadzikirapp.adapter.ArticleAdapter
import com.ojaq.doadzikirapp.databinding.ActivityMainBinding
import com.ojaq.doadzikirapp.model.ArticleItem
import com.ojaq.doadzikirapp.presentation.DetailArticleActivity
import com.ojaq.doadzikirapp.presentation.DzikirHarianActivity
import com.ojaq.doadzikirapp.presentation.DzikirSetiapSaatActivity
import com.ojaq.doadzikirapp.presentation.pagipetang.PagiPetangActivity
import com.ojaq.doadzikirapp.presentation.QouliyahShalatActivity
import com.ojaq.doadzikirapp.utils.OnItemCallback

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var dotSliderIndicator = arrayOf<ImageView?>()

    //OnPageChangeCallback is SubClass from viewPager2 to respond the changing state of the selected page
    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        //instance onPageSelected gives you access to set behavior state of selected page
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            for (i in initData().indices) {
                dotSliderIndicator[i]?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.inactive_dot)
                )
            }

            dotSliderIndicator[position]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.active_dot)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //this method is from dependencies splash screen api 12
        installSplashScreen()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setupViewPager()
    }

    private fun setupViewPager() {
        dotSliderIndicator = arrayOfNulls(initData().size)

        for (i in initData().indices) {
            dotSliderIndicator[i] = ImageView(this)
            dotSliderIndicator[i]?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.inactive_dot)
            )
            val params = LinearLayoutCompat.LayoutParams(35, 35)
            params.marginStart = 8
            params.marginEnd = 8
            binding.dots.addView(dotSliderIndicator[i], params)
        }
    }

    private fun initView() {
        // setContentView is used to display layout in activity
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // declare variable to get in touch with item in layout activity_main
        // use findViewById to communicate with the view
        // can be replaced by viewBinding
        val cardQouliyahShalat = findViewById<MaterialCardView>(R.id.card_qouliyah)
        val cardDzikir = findViewById<MaterialCardView>(R.id.card_dzikir)
        val cardDzikirHarian = findViewById<MaterialCardView>(R.id.card_harian)
        val cardDzikirPagiPetang = findViewById<MaterialCardView>(R.id.card_pagi_petang)

        // when cardView is clicked it'll be navigated to other page
        // intent is used for navigating to other activity by clicking cardView
        cardQouliyahShalat.setOnClickListener {
            val intent = Intent(this, QouliyahShalatActivity::class.java)
            // go to the destination activity
            startActivity(intent)
        }

        cardDzikir.setOnClickListener(this)
        cardDzikirHarian.setOnClickListener(this)
        cardDzikirPagiPetang.setOnClickListener(this)

        val mAdapter = ArticleAdapter()
        mAdapter.setData(initData())
        mAdapter.setOnItemCallback(object : OnItemCallback {
            override fun onItemClicked(item: ArticleItem) {
                //navigate to Detail Activity
                val intent = Intent(this@MainActivity, DetailArticleActivity::class.java)
                //navigate to DetailActivity with data using putExtra
                intent.putExtra("title", item.titleArticle)
                intent.putExtra("content", item.contentArticle)
                intent.putExtra("image", item.imageArticle)
                startActivity(intent)
            }

        })
        binding.vpArticle.adapter = mAdapter
        binding.vpArticle.registerOnPageChangeCallback(slidingCallback)
    }

    private fun initData(): List<ArticleItem> {
        val titleArticle = resources.getStringArray(R.array.arr_title_artikel)
        val contentArticle = resources.getStringArray(R.array.arr_desc_artikel)
        val imageArticle = resources.obtainTypedArray(R.array.arr_img_artikel)

        val listData = arrayListOf<ArticleItem>()
        for (index in titleArticle.indices) {
            val data = ArticleItem(
                titleArticle[index],
                imageArticle.getResourceId(index, 0),
                contentArticle[index]
            )
            listData.add(data)
        }
        imageArticle.recycle()
        return listData
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.card_dzikir -> startActivity(Intent(this, DzikirSetiapSaatActivity::class.java))
            R.id.card_harian -> startActivity(Intent(this, DzikirHarianActivity::class.java))
            R.id.card_pagi_petang -> startActivity(Intent(this, PagiPetangActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}