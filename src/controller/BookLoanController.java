package controller;

import entity.BookLoan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookLoanController {
    private final List<BookLoan> bookLoans = new ArrayList<>();
    private static final String FILE_NAME = "book_loans.data";

    public BookLoanController() {
        loadBookLoansFromFile();
    }

    public void addBookLoan(BookLoan bookLoan) {
        bookLoans.add(bookLoan);
        saveBookLoansToFile();
    }

    public boolean updateBookLoan(int id, BookLoan updatedBookLoan) {
        for (int i = 0; i < bookLoans.size(); i++) {
            if (bookLoans.get(i).getId() == id) {
                bookLoans.set(i, updatedBookLoan);
                saveBookLoansToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteBookLoan(int id) {
        boolean removed = bookLoans.removeIf(bookLoan -> bookLoan.getId() == id);
        if (removed) {
            saveBookLoansToFile();
        }
        return removed;
    }

    public List<BookLoan> getAllBookLoans() {
        return bookLoans;
    }

    public BookLoan getBookLoanById(int id) {
        return bookLoans.stream()
                .filter(bookLoan -> bookLoan.getId() == id)
                .findFirst()
                .orElse(null);
    }
    private void saveBookLoansToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(bookLoans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBookLoansFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<BookLoan> loadedBookLoans = (List<BookLoan>) in.readObject();
            bookLoans.clear();
            bookLoans.addAll(loadedBookLoans);
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
