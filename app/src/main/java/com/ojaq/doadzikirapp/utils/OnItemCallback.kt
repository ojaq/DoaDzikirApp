package com.ojaq.doadzikirapp.utils

import com.ojaq.doadzikirapp.model.ArticleItem

interface OnItemCallback {
    fun onItemClicked(item: ArticleItem)
}