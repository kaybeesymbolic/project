package com.placodes.utils

import com.placodes.models.Article
import com.placodes.models.Book


fun Book.toLink(): Article = Article(book = this, link = "/books/${this.id}/read")




fun Iterable<Book>.toLinks(): Iterable<Article> = this.map {it.toLink() }
