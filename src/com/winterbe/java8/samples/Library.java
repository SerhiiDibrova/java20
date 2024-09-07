package com.winterbe.java20.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Library {

    private String name;
    private List<Book> books;

    public Library(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Library name cannot be empty");
        }
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new NullPointerException("Book cannot be null");
        }
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }

    public void printBooks(Consumer<String> printer) {
        books.forEach(book -> printer.accept(book.toString()));
    }

    public static record Book(String title, String author, int pages) {}

    public static void main(String[] args) {
        var library = new Library("Central Library");

        library.addBook(new Book("Java 20 in Action", "Raoul-Gabriel Urma", 424));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 416));
        library.addBook(new Book("Clean Code", "Robert C. Martin", 464));

        library.printBooks(System.out::println);

        var thickBooks = library.getBooks().stream()
                .filter(book -> book.pages() > 400)
                .collect(Collectors.toList());

        thickBooks.forEach(System.out::println);
    }
}