package com.ojaq.doadzikirapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ojaq.doadzikirapp.databinding.ItemArticleBinding
import com.ojaq.doadzikirapp.model.ArticleItem
import com.ojaq.doadzikirapp.presentation.DetailArticleActivity
import com.ojaq.doadzikirapp.utils.OnItemCallback

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(){
    //variable to store dataset
    private var listArticle = ArrayList<ArticleItem>()
    //variable to give event click on item in Activity through fun setOnItemClickCallback
    private var onItemCallback: OnItemCallback? = null

    inner class ArticleViewHolder(val view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root)

    fun setData(list: List<ArticleItem>){
        listArticle.clear()
        listArticle.addAll(list)
    }
    //fun to set event click in item using interface
    fun setOnItemCallback(onItemCallback: OnItemCallback){
        this.onItemCallback = onItemCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = listArticle.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val data = listArticle[position]
        holder.view.itemArticle.setImageResource(data.imageArticle)
        //this gives a click event for each item in ViewPager
        holder.itemView.setOnClickListener {
            //set event click in activity through interface
            onItemCallback?.onItemClicked(data)

            //set event click directly through adapter
            //provide context for intent
//            it.context.apply {
//                val intent = Intent(this, DetailArticleActivity::class.java)
//                //navigate to DetailActivity with data using putExtra
//                intent.putExtra("title", data.titleArticle)
//                intent.putExtra("content", data.contentArticle)
//                intent.putExtra("image", data.imageArticle)
//                startActivity(intent)
//            }
        }
    }
}