package controller;

import entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookController {
    private final List<Book> books = new ArrayList<>();
    private static final String FILE_NAME = "books.data";

    public BookController() {
        loadBooksFromFile();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile();
    }

    public boolean updateBook(int id, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, updatedBook);
                saveBooksToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(int id) {
        boolean removed = books.removeIf(book -> book.getId() == id);
        if (removed) {
            saveBooksToFile();
        }
        return removed;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void saveBooksToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBooksFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Book> loadedBooks = (List<Book>) in.readObject();
            books.clear();
            books.addAll(loadedBooks);
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}