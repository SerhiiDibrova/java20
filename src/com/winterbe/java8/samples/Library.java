package com.winterbe.java20.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Library {

    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = Objects.requireNonNull(name, "Library name cannot be null");
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(Objects.requireNonNull(book, "Book cannot be null"));
    }

    public List<Book> getBooks() {
        return List.copyOf(books);
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
            this.title = Objects.requireNonNull(title, "Book title cannot be null");
            this.author = Objects.requireNonNull(author, "Book author cannot be null");
            if (pages < 0) {
                throw new IllegalArgumentException("Number of pages must not be negative");
            }
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
        Library library = new Library("Central Library");

        library.addBook(new Book("Java 8 in Action", "Raoul-Gabriel Urma", 424));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 416));
        library.addBook(new Book("Clean Code", "Robert C. Martin", 464));

        library.printBooks();

        List<Book> thickBooks = library.getBooks().stream()
                .filter(book -> book.getPages() > 400)
                .collect(Collectors.toList());

        thickBooks.forEach(System.out::println);
    }
}