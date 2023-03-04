package com.vn.DAO;

import com.vn.model.Student;
import com.vn.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImplement implements StudentDao {

    @Override
    public Boolean saveAll(List<Student> students) {

        try (Connection conn = DBUtils.getConnection()) {
            String sql = "INSERT INTO STUDENT VALUES (?, ?) ";
            PreparedStatement stm = conn.prepareStatement(sql);

            int rows = 0;
            for (Student student : students) {
                stm.setString(1, student.getName());
                stm.setDouble(2, student.getMark());
                stm.executeUpdate();
                rows++;
            }

            System.out.println("record inserted: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Student save(Student student) {
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "INSERT INTO STUDENT VALUES (?, ?) ";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(1, student.getName());
            stm.setDouble(2, student.getMark());

            int row = stm.executeUpdate();
            System.out.println("record inserted : " + row);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }


    @Override
    public List<Student> readAll() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection()) {
            //1. Add lib(implementation)
            //4 Connection => SQL => Statement
            Statement stm = conn.createStatement();
            //5 Statement => execute statement => ResultSet
            String sql = "SELECT * FROM Student";
            ResultSet rs = stm.executeQuery(sql);
            //6 Conversion ResultSet => List<Student>
            while (rs.next()) {
                Integer idStudent = rs.getInt(1);
                String name = rs.getString("name");
                Double mark = rs.getDouble(3);

                Student student = new Student(idStudent, name, mark);
                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }


    @Override
    public Student readDetail(Integer ID) {
        try (Connection conn = DBUtils.getConnection()) {
            Statement stm = conn.createStatement();

            String sql = "SELECT * FROM STUDENT WHERE id = " + ID;

//            PreparedStatement stm = conn.prepareStatement(sql);
//            stm.setInt(1, ID);

            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Integer id = rs.getInt(1);
                String name = rs.getString(2);
                Double mark = rs.getDouble(3);

                Student stu = new Student(id, name, mark);

                return stu;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public Student update(Student student) {
        try (Connection conn = DBUtils.getConnection()) {
            Student stu = new Student();
            stu = readDetail(student.getID());

            String sql = "UPDATE STUDENT SET name = ?, mark = ? WHERE id = ? ";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, student.getName());
            stm.setDouble(2, student.getMark());
            stm.setInt(3, student.getID());

            int row = stm.executeUpdate();
            System.out.println("record updated: " + row);

            return stu;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Student delete(Integer ID) {
        try (Connection conn = DBUtils.getConnection()) {

            Student stu = new Student();
            stu = readDetail(ID);

            Statement stm = conn.createStatement();

            String sql = "DELETE FROM STUDENT  WHERE  id = " + ID;

            int row = stm.executeUpdate(sql);
            System.out.println("row deleted: " + row);

            return stu;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<Student> deleteAll(List<Integer> IDs) {

        try (Connection conn = DBUtils.getConnection()) {

            String sql = "DELETE FROM STUDENT  WHERE  id =? ";
            PreparedStatement stm = conn.prepareStatement(sql);

            List<Student> stuDelete = new ArrayList<>();

            int rows = 0;
            for (Integer in : IDs) {

                if (readDetail(in) != null) {
                    stuDelete.add(readDetail(in));
                }
                stm.setInt(1, in);
                rows += stm.executeUpdate();
            }

            System.out.println("rows deleted: " + rows);

            return stuDelete;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


//
//    @Override
//    public Student saveUseStore(Student student) {
//        try (Connection conn = DBUtils.getConnection()) {
//            String sql = "{ CALL sp_Student_insert (?, ?, ?, ?)} ";
//
//            CallableStatement stm = conn.prepareCall(sql);
//
//            stm.setString(1, student.getName());
//            stm.setDouble(2, student.getMark());
//
//            stm.registerOutParameter(3, Types.VARCHAR);
//            stm.registerOutParameter(4, Types.VARCHAR);
//
//            stm.execute();
//
//            System.out.println("Output 1: " + stm.getString(3));
//            System.out.println("Output 2: " + stm.getString(4));
////            System.out.println("Done");
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }


    @Override
    public Boolean saveAllByTransaction(List<Student> students) {
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "INSERT INTO STUDENT VALUES (?, ?) ";
            PreparedStatement stm = conn.prepareStatement(sql);

            int rows = 0;
            conn.setAutoCommit(false);

            for (Student student : students) {
                stm.setString(1, student.getName());
                stm.setDouble(2, student.getMark());
                rows += stm.executeUpdate();

            }
            System.out.println("record inserted: " + rows);

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public Boolean saveAllByBatch(List<Student> students) {
        try (Connection conn = new DBUtils().getConnection()) {
            String sql = "INSERT INTO STUDENT VALUES (?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql); // 5 Statement => execute => ResultSet
            int row = 0;
            conn.setAutoCommit(false);

            for (Student student : students) {
                stm.setString(1, student.getName());
                stm.setDouble(2, student.getMark());

                stm.addBatch();
                row++;
            }
            System.out.println("Record inserted : " + row);
            stm.executeBatch();

            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}


