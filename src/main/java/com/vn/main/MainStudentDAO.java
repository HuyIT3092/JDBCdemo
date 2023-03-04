package com.vn.main;

import com.vn.DAO.StudentDAOImplement;
import com.vn.DAO.StudentDao;
import com.vn.model.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MainStudentDAO {
    StudentDao studentDao = new StudentDAOImplement();

    @Test
    public void test_readAll() {
        MainStudentDAO main = new MainStudentDAO();

        List<Student> students = main.studentDao.readAll();

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void test_readDetail() {
        MainStudentDAO main = new MainStudentDAO();

        Student stu = main.studentDao.readDetail(3);
        System.out.println(stu);
    }

    @Test
    public void test_saveAllByBatch() {
        MainStudentDAO main = new MainStudentDAO();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            students.add(new Student(0, "John", 9.5));
        }
        long start = System.currentTimeMillis();
        main.studentDao.saveAllByBatch(students);
        long end = System.currentTimeMillis();
        System.out.println("Time by batch: " + (end - start));
    }


    @Test
    public void test_saveAllByTransaction() {
        MainStudentDAO main = new MainStudentDAO();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            students.add(new Student(0, "John", 9.5));
        }
        long start = System.currentTimeMillis();
        main.studentDao.saveAllByTransaction(students);
        long end = System.currentTimeMillis();
        System.out.println("Time by transaction: " + (end - start));
    }

    @Test
    public void test_saveAll() {
        MainStudentDAO main = new MainStudentDAO();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            students.add(new Student(0, "John", 9.5));
        }
        long start = System.currentTimeMillis();
        main.studentDao.saveAll(students);
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
    }


    @Test
    public void test_deleteAll() {
        MainStudentDAO main = new MainStudentDAO();

        List<Integer> idDelete = new ArrayList<>();
        idDelete.add(7);
        idDelete.add(14);
        idDelete.add(3);

        List<Student> listDelete = new ArrayList<>();
        listDelete = main.studentDao.deleteAll(idDelete);

        for (Student st : listDelete) {
            System.out.println(st);
        }


    }


    public static void main(String[] args) {
        MainStudentDAO main = new MainStudentDAO();
        List<Student> students = main.studentDao.readAll();

        for (Student student : students) {
            System.out.println(student);
        }


        Student stu = new Student(null, "dang huy", 8.8);

//        main.studentDao.save(stu);

        List<Student> addStu = new ArrayList<>();
        addStu.add(new Student(null, "Hoai Thuong", 8.9));
        addStu.add(new Student(null, "Tien Dung", 9.9));
        addStu.add(new Student(null, "Gia Bao", 8.3));
        addStu.add(new Student(null, "cu Co", 10.));
        addStu.add(new Student(null, "Nguyen Bin", 9.5));

        main.studentDao.saveAll(addStu);
        int idSearch = 11;
        Student stu1 = main.studentDao.readDetail(idSearch);
        System.out.println(stu1);

    }
}
