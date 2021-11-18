package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO implements DAO{

    public StudentDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root",
                "admin");
    }

    public int getTotal() {
        int total = 0;
        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from student";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }

            System.out.println("total:" + total);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    public void add(Student stu) {

        String sql = "insert into student values(null,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, stu.number);
            ps.setString(2, stu.name);
            ps.setFloat(3, stu.gpa);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                stu.id = id;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void update(Student stu) {

        String sql = "update student set number= ?, name = ? , gpa = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, stu.number);
            ps.setString(2, stu.name);
            ps.setFloat(3, stu.gpa);
            ps.setInt(4, stu.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from student where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Student get(int id) {
        Student stu = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from student where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                stu = new Student();
                String name = rs.getString(3);
                float gpa = rs.getFloat("gpa");
                int number = rs.getInt(2);
                stu.name = name;
                stu.number = number;
                stu.gpa = gpa;
                stu.id = id;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return stu;
    }

    public List<Student> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Student> list(int start, int count) {
        List<Student> students = new ArrayList<>();

        String sql = "select * from student order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student stu = new Student();
                int id = rs.getInt(1);
                int number = rs.getInt(2);
                String name = rs.getString(3);
                float gpa = rs.getFloat("gpa");
                stu.id = id;
                stu.number = number;
                stu.name = name;
                stu.gpa = gpa;
                students.add(stu);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return students;
    }

}