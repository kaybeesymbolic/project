package com.placodes.models

import com.placodes.db.BooksRepository
import com.placodes.utils.toLinks
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList


val books = mutableListOf<List<Article>>()
val previous = mutableListOf<List<Article>>()
var current = listOf<Article>()
var last = listOf<Article>()
val tempPrevious = mutableListOf<List<Article>>()



fun mapIt(user: Any?, currents: List<Article>? = null): Map<Any?, Any?> {
    if(previous.isEmpty()) previous.addAll(tempPrevious)
    val res = currents?: current
    return mapOf(
        "user" to user,
        "books" to res,
        "current" to books,
        "previous" to previous
    )
}
suspend fun next(user: Any?) : Map<Any?, Any?> {
    val offset = books.flatMap { it}.toList().size.toLong()
    if(current.isNotEmpty()) previous.add(current)

    BooksRepository.pages(offset).apply {
        if(this.isNotEmpty()) current = this.toLinks() as List<Article>
        books.add(current)
    }
    return mapIt(user, current)
}

fun previous(user: Any?):Map<Any?, Any?>{
    if(previous.isNotEmpty()) last = previous.removeLast()
    if(last.isNotEmpty()) tempPrevious.add(last)
    return mapIt(user, last)
}




fun findArticle(id:Int): Article? = books.flatten().firstOrNull { it.book?.id?.toInt() == id }


