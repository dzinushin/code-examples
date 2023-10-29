package com.dz.books

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping("")
    fun books(): List<Book> {
        return listOf(Book("book1"), Book("book2"))
    }
}
