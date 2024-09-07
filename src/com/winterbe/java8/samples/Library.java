package com.winterbe.java20.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }

    public void printBooks() {
        books.forEach(book -> System.out.println(book.title()));
    }

    public static record Book(String title, String author, int pages) {}

    public static void main(String[] args) {
        var library = new Library("Central Library");

        library.addBook(new Book("Java 20 in Action", "Raoul-Gabriel Urma", 424));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 416));
        library.addBook(new Book("Clean Code", "Robert C. Martin", 464));

        library.printBooks();

        var thickBooks = library.getBooks().stream()
                .filter(book -> book.pages() > 400)
                .collect(Collectors.toList());

        thickBooks.forEach(System.out::println);
    }
}