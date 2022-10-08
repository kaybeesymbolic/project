package com.placodes.pages

import com.placodes.auth.getUserInfo
import com.placodes.db.BooksRepository
import com.placodes.models.*
import com.placodes.models.toDto
import io.ktor.server.application.*
import io.ktor.server.mustache.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*


fun Route.bookRoute() = route("/"){
    get("books"){
        val user = getUserInfo(call)
        if(user == null) call.respondRedirect("/error")
        val userDto = user?.toDto() as UserDto
        val mapper = mapIt(userDto)
        call.respond(MustacheContent("book/books.hbs", mapper))
    }

    post("books/add"){
        val user = getUserInfo(call)
        if(user == null) call.respondRedirect("/login")
        val forms = call.receiveParameters()
        val title = forms.getOrFail("title")
        val content = forms.getOrFail("content")
        val userDto = user?.toDto() as UserDto
        val book = Book(name= title, author = userDto.name, content = content, authorId = userDto.id)
        val mapper = next(userDto)
        BooksRepository.book(book).apply {
            if(this > 0)  call.respond(MustacheContent("index/index.hbs", mapper))
        }
    }
    route("books/"){
       get("{id}/read"){
               val id = call.parameters["id"] ?: return@get call.respondRedirect("/error")
               val article = findArticle(id.toInt())
               call.respond(MustacheContent("book/read.hbs", mapOf("article" to article)))
       }
        get("contents"){
               val user = getUserInfo(call)
               if(user == null) call.respondRedirect("/error")
               val userDto = user?.toDto()
               val mapper = next(userDto)
               call.respond(MustacheContent("book/contents.hbs", mapper))
        }
        get("next") {
               val user = getUserInfo(call)
               val dto = user?.toDto()
               val mapper = next(dto)
               call.respond(MustacheContent("book/contents.hbs", mapper))
        }
        get("previous") {
               val user = getUserInfo(call)
               val dto = user?.toDto()
               val mapper = previous(dto)
               call.respond(MustacheContent("book/contents.hbs", mapper))
        }

    }

}