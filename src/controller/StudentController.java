package controller;

import entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private final List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.data";

    public StudentController() {
        loadStudentsFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
    }

    public boolean updateStudent(int id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.set(i, updatedStudent);
                saveStudentsToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        boolean removed = students.removeIf(student -> student.getId() == id);
        if (removed) {
            saveStudentsToFile();
        }
        return removed;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Student> searchStudents(String keyword) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    private void saveStudentsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Student> loadedStudents = (List<Student>) in.readObject();
            students.clear();
            students.addAll(loadedStudents);
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}