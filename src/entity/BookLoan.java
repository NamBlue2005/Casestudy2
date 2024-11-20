package entity;

import controller.StudentController;
import controller.BookController;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookLoan implements Serializable {
    private int id;
    private Student student;
    private Book book;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public BookLoan(int id, Student student, Book book, Date loanDate, Date dueDate, Date returnDate) {
        this.id = id;
        this.student = student;
        this.book = book;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public BookLoan(int id, Student student, Book book, Date loanDate, Date dueDate) {
        this(id, student, book, loanDate, dueDate, null);
    }

    public BookLoan(int id, int studentId, int bookId) {
        StudentController studentController = new StudentController();
        BookController bookController = new BookController();

        this.student = studentController.getStudentById(studentId);
        this.book = bookController.getBookById(bookId);

        if (this.student == null || this.book == null) {
            System.out.println("Không tìm thấy sinh viên hoặc sách với các ID đã cho!");
        }
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        try {
            this.loanDate = DATE_FORMAT.parse(loanDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        try {
            this.dueDate = DATE_FORMAT.parse(dueDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(String returnDate) {
        try {
            if (returnDate != null && !returnDate.isEmpty()) {
                this.returnDate = DATE_FORMAT.parse(returnDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        String returnDateStr = (returnDate != null) ? DATE_FORMAT.format(returnDate) : "Chưa trả";
        return String.format("Phiếu mượn ID: %d, Học sinh: %s, Sách: %s, Ngày mượn: %s, Ngày trả: %s, Ngày trả thực tế: %s",
                id, student, book, DATE_FORMAT.format(loanDate), DATE_FORMAT.format(dueDate), returnDateStr);
    }
    public void setStudentId(int studentId) {
        StudentController studentController = new StudentController();
        Student student = studentController.getStudentById(studentId);
        if (student != null) {
            this.student = student;
        } else {
            System.out.println("Sinh viên có ID " + studentId + " không tìm thấy!");
        }
    }
    public void setBookId(int bookId) {
        BookController bookController = new BookController();
        Book book = bookController.getBookById(bookId);
        if (book != null) {
            this.book = book;
        } else {
            System.out.println("Sách có ID " + bookId + " không tìm thấy!");
        }
    }
}