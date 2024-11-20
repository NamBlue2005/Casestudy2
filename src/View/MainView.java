package View;

import controller.BookController;
import controller.BookLoanController;
import controller.StudentController;
import entity.Book;
import entity.BookLoan;
import entity.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentController studentController = new StudentController();
    private static final BookController bookController = new BookController();
    private static final BookLoanController bookLoanController = new BookLoanController();

    public static void main(String[] args) throws ParseException {
        while (true) {
            System.out.println("\n=== Quản Lý Thư Viện ===");
            System.out.println("1. Quản lý học sinh");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Quản lý phiếu mượn");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> manageStudents();
                case 2 -> manageBooks();
                case 3 -> manageBookLoans();
                case 4 -> {
                    System.out.println("Đã thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void manageStudents() {
        while (true) {
            System.out.println("\n=== Quản Lý Học Sinh ===");
            System.out.println("1. Thêm học sinh");
            System.out.println("2. Sửa học sinh");
            System.out.println("3. Xóa học sinh");
            System.out.println("4. Hiển thị danh sách học sinh");
            System.out.println("5. Tìm kiếm học sinh");
            System.out.println("6. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> displayStudents();
                case 5 -> searchStudent();
                case 6 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void addStudent() {
        try {
            System.out.print("Nhập ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập tên: ");
            String name = scanner.nextLine();
            System.out.print("Nhập lớp: ");
            String className = scanner.nextLine();

            Student student = new Student(id, name, className);
            studentController.addStudent(student);
            System.out.println("Thêm học sinh thành công!");
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
        }
    }

    private static void updateStudent() {
        try {
            System.out.print("Nhập ID học sinh cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine());
            Student student = studentController.getStudentById(id);
            if (student == null) {
                System.out.println("Không tìm thấy học sinh!");
                return;
            }

            System.out.print("Nhập tên mới: ");
            String name = scanner.nextLine();
            System.out.print("Nhập lớp mới: ");
            String className = scanner.nextLine();

            Student updatedStudent = new Student(id, name, className);
            if (studentController.updateStudent(id, updatedStudent)) {
                System.out.println("Sửa thông tin học sinh thành công!");
            } else {
                System.out.println("Sửa thông tin thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
        }
    }

    private static void deleteStudent() {
        try {
            System.out.print("Nhập ID học sinh cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (studentController.deleteStudent(id)) {
                System.out.println("Xóa học sinh thành công!");
            } else {
                System.out.println("Không tìm thấy học sinh!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
        }
    }

    private static void displayStudents() {
        System.out.println("Danh sách học sinh:");
        studentController.getAllStudents().forEach(System.out::println);
    }

    private static void searchStudent() {
        System.out.print("Nhập tên học sinh để tìm kiếm: ");
        String keyword = scanner.nextLine();
        var result = studentController.searchStudents(keyword);
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy học sinh!");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            result.forEach(System.out::println);
        }
    }

    private static void manageBooks() {
        while (true) {
            System.out.println("\n=== Quản Lý Sách ===");
            System.out.println("1. Thêm sách");
            System.out.println("2. Sửa sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Hiển thị danh sách sách");
            System.out.println("5. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> updateBook();
                case 3 -> deleteBook();
                case 4 -> displayBooks();
                case 5 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void addBook() {
        try {
            System.out.print("Nhập ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập tên sách: ");
            String title = scanner.nextLine();
            System.out.print("Nhập tác giả: ");
            String author = scanner.nextLine();
            System.out.print("Nhập nhà xuất bản: ");
            String publisher = scanner.nextLine();
            System.out.print("Nhập năm xuất bản: ");
            int year = Integer.parseInt(scanner.nextLine());

            Book book = new Book(id, title, author, publisher, year);
            bookController.addBook(book);
            System.out.println("Thêm sách thành công!");
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
        }
    }

    private static void updateBook() {
        try {
            System.out.print("Nhập ID sách cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine());
            Book book = bookController.getBookById(id);
            if (book == null) {
                System.out.println("Không tìm thấy sách!");
                return;
            }

            System.out.print("Nhập tên sách mới: ");
            String title = scanner.nextLine();
            System.out.print("Nhập tác giả mới: ");
            String author = scanner.nextLine();
            System.out.print("Nhập nhà xuất bản mới: ");
            String publisher = scanner.nextLine();
            System.out.print("Nhập năm xuất bản mới: ");
            int year = Integer.parseInt(scanner.nextLine());

            Book updatedBook = new Book(id, title, author, publisher, year);
            if (bookController.updateBook(id, updatedBook)) {
                System.out.println("Sửa thông tin sách thành công!");
            } else {
                System.out.println("Sửa thông tin thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
        }
    }

    private static void deleteBook() {
        try {
            System.out.print("Nhập ID sách cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (bookController.deleteBook(id)) {
                System.out.println("Xóa sách thành công!");
            } else {
                System.out.println("Không tìm thấy sách!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
        }
    }

    private static void displayBooks() {
        System.out.println("Danh sách sách:");
        bookController.getAllBooks().forEach(System.out::println);
    }

    private static void manageBookLoans() throws ParseException {
        while (true) {
            System.out.println("\n=== Quản Lý Phiếu Mượn ===");
            System.out.println("1. Thêm phiếu mượn");
            System.out.println("2. Sửa phiếu mượn");
            System.out.println("3. Xóa phiếu mượn");
            System.out.println("4. Hiển thị danh sách phiếu mượn");
            System.out.println("5. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> addBookLoan();
                case 2 -> updateBookLoan();
                case 3 -> deleteBookLoan();
                case 4 -> displayBookLoans();
                case 5 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void addBookLoan() throws ParseException {
        try {
            System.out.print("Nhập ID phiếu mượn: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập ID học sinh: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập ID sách: ");
            int bookId = Integer.parseInt(scanner.nextLine());

            Student student = studentController.getStudentById(studentId);
            Book book = bookController.getBookById(bookId);

            if (student == null || book == null) {
                System.out.println("Không tìm thấy học sinh hoặc sách!");
                return;
            }

            System.out.print("Nhập ngày mượn (yyyy-MM-dd): ");
            Date loanDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
            System.out.print("Nhập ngày trả (yyyy-MM-dd): ");
            Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());

            BookLoan bookLoan = new BookLoan(id, student, book, loanDate, dueDate);
            bookLoanController.addBookLoan(bookLoan);
            System.out.println("Thêm phiếu mượn thành công!");
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
        }
    }

    private static void updateBookLoan() throws ParseException {
        try {
            System.out.print("Nhập ID phiếu mượn cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine());
            BookLoan bookLoan = bookLoanController.getBookLoanById(id);
            if (bookLoan == null) {
                System.out.println("Không tìm thấy phiếu mượn!");
                return;
            }

            System.out.print("Nhập ID học sinh mới: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập ID sách mới: ");
            int bookId = Integer.parseInt(scanner.nextLine());

            Student student = studentController.getStudentById(studentId);
            Book book = bookController.getBookById(bookId);

            if (student == null || book == null) {
                System.out.println("Không tìm thấy học sinh hoặc sách!");
                return;
            }

            System.out.print("Nhập ngày mượn mới (dd/MM/yyyy): ");
            Date loanDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
            System.out.print("Nhập ngày trả mới (dd/MM/yyyy): ");
            Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());

            BookLoan updatedBookLoan = new BookLoan(id, student, book, loanDate, dueDate);
            if (bookLoanController.updateBookLoan(id, updatedBookLoan)) {
                System.out.println("Sửa phiếu mượn thành công!");
            } else {
                System.out.println("Sửa phiếu mượn thất bại!");
            }
        } catch (NumberFormatException | ParseException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập đúng định dạng.");
        }
    }

    private static void deleteBookLoan() {
        try {
            System.out.print("Nhập ID phiếu mượn cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (bookLoanController.deleteBookLoan(id)) {
                System.out.println("Xóa phiếu mượn thành công!");
            } else {
                System.out.println("Không tìm thấy phiếu mượn!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
        }
    }

    private static void displayBookLoans() {
        System.out.println("Danh sách phiếu mượn:");
        bookLoanController.getAllBookLoans().forEach(System.out::println);
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi nhập liệu. Vui lòng nhập số hợp lệ.");
            }
        }
    }
}