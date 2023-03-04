package com.vn.DAO;

import java.util.List;

import com.vn.model.Student;
public interface StudentDao {
    //CRUD Ctrl Shift O
    Boolean saveAll(List<Student> students);
    Student save(Student student);
    List<Student> readAll();
    Student readDetail(Integer ID);
    Student update(Student student);
    Student delete(Integer ID);
    List<Student> deleteAll(List<Integer> IDs);

    public Boolean saveAllByBatch(List<Student> students);

    public Boolean saveAllByTransaction(List<Student> students);




}
