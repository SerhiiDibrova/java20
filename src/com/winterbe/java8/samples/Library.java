package com.winterbe.java8.samples;

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
        books.forEach(book -> System.out.println(book.getTitle()));
    }

    public static class Book {

        private String title;
        private String author;
        private int pages;

        public Book(String title, String author, int pages) {
            this.title = title;
            this.author = author;
            this.pages = pages;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public int getPages() {
            return pages;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", pages=" + pages +
                    '}';
        }
    }

    public static void main(String[] args) {
        var library = new Library("Central Library");

        library.addBook(new Book("Java 8 in Action", "Raoul-Gabriel Urma", 424));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 416));
        library.addBook(new Book("Clean Code", "Robert C. Martin", 464));

        library.printBooks();

        var thickBooks = library.getBooks().stream()
                .filter(book -> book.getPages() > 400)
                .collect(Collectors.toList());

        thickBooks.forEach(System.out::println);
    }
}