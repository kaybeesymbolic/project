package com.placodes.db

import com.placodes.models.Book
import com.placodes.models.Books
import com.placodes.models.Books.author
import com.placodes.models.Books.authorId
import com.placodes.models.Books.content
import com.placodes.models.Books.id
import com.placodes.models.Books.name
import com.placodes.models.Books.published
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement


object BooksRepository: BooksInterface{

    override suspend fun books(): List<Book> = query { Books.selectAll().mapNotNull { toBook(it) } }

    override suspend fun book(id: Int): Book? = query { Books.select { (Books.id.eq(id)) }.mapNotNull { toBook(it) }.singleOrNull()}

    override suspend fun book(book: Book): Int  = query { Books.insert { toInsert(it,book) } }[id]

    override suspend fun edit(id: Int, book: Book): Boolean = query { Books.update {toEdit(it, book) } } > 0

    override suspend fun delete(id: Int): Boolean = query { Books.deleteWhere { Books.id.eq(id) }} > 0

    override suspend fun pages(offset:Long, limit: Int) = query { Books.selectAll().limit(limit,offset=offset).mapNotNull { toBook(it) } }


    /* data model for updating database entity */
    private fun toEdit(it: UpdateStatement, book: Book){
        it[name] = book.name
        it[content] = book.content
        it[author] = book.author
        it[authorId] = book.authorId
        it[published] = book.published
    }

    /* convert row to book data */
    private fun toBook(it: ResultRow): Book {
        val id = it[Books.id]
        val name = it[Books.name]
        val content = it[Books.content]
        val author = it[Books.author]
        val authorId = it[Books.authorId]
        val published = it[published]


        return Book(
            id = id.toString(),
            name = name,
            content = content,
            author = author,
            authorId = authorId,
            published = published,
        )
    }

    /* helper function for inserting */
    private fun toInsert(it: InsertStatement<Number>, book: Book){
        it[name] = book.name
        it[content] = book.content
        it[author] = book.author
        it[authorId] = book.authorId
        it[published] = book.published
    }

}


interface BooksInterface {
    suspend fun books(): List<Book> /* find all books */
    suspend fun pages(offset: Long=0, limit: Int=5): List<Book> /* paginate */
    suspend fun book(id:Int): Book? /* find book with id */
    suspend fun book(book: Book):Int /* insert book */
    suspend fun edit(id:Int, book: Book):Boolean /* edit book with id with given book */
    suspend fun delete(id: Int):Boolean /* delete book with id */
}