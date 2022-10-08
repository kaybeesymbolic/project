package com.placodes.models

import com.placodes.timezone.TimeZoneHelperImpl
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Book(
    val id: String? = null,
    val name: String,
    val content: String,
    val author: String,
    val authorId: String,
    val published: String = TimeZoneHelperImpl.currentTime()
)

@Serializable
data class Article(
    val book: Book? = null,
    val link: String? = null
)

@Serializable
data class Reaction(
    val id: String? = null,
    val likes: Int = 0,
    val dislikes: Int = 0,
    val reactor: String,
    val response: String = "This is fantastic, wow!!",
    val published: String = TimeZoneHelperImpl.currentTime()
)


object Reactions: Table(){
    val id = integer("id").autoIncrement()
    val likes = integer("likes").default(0)
    val dislikes = integer("dislikes").default(0)
    val reactor = varchar("reactor", 64)
    val response = varchar("response", 512)
    val published = varchar("published", 64)

    //val bookId = (integer("book_id") references Books.id).autoIncrement()

    override val primaryKey = PrimaryKey(id)
}
object Books : Table(){
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val content = text("content")
    val author = varchar("author", 128)
    val authorId = varchar("authorId", 512)
    val published = varchar("published", 64)

    override val primaryKey = PrimaryKey(id, name= "book_id")
}