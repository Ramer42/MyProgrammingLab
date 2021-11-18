package model;

import java.util.List;

public interface DAO{
    //add
    public void add(Student stu);
    //modify
    public void update(Student stu);
    //delete
    public void delete(int id);
    //get
    public Student get(int id);
    //retrieve
    public List<Student> list();
    //page retrieve
    public List<Student> list(int start, int count);
}