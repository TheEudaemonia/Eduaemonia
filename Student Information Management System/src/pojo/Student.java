package pojo;

import java.util.Objects;

//学生类设计
public class Student {
    private String name;
    private String ID;
    private double balance;
    private String sex;
    private double GradeOne;
    private double GradeTwo;
    private double GradeThree;

    @Override
    public String toString() {
        return name + "," + ID + "," + sex + "," + balance + "," + GradeOne + "," + GradeTwo + "," + GradeThree + ";";
    }

    public Student() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(balance, student.balance) == 0 && Double.compare(GradeOne, student.GradeOne) == 0 && Double.compare(GradeTwo, student.GradeTwo) == 0 && Double.compare(GradeThree, student.GradeThree) == 0 && Objects.equals(name, student.name) && Objects.equals(ID, student.ID) && Objects.equals(sex, student.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ID, sex, balance, GradeOne, GradeTwo, GradeThree);
    }

    public Student(String name, String ID, String sex, double balance, double gradeOne, double gradeTwo, double gradeThree) {
        this.name = name;
        this.ID = ID;
        this.balance = balance;
        this.sex = sex;
        GradeOne = gradeOne;
        GradeTwo = gradeTwo;
        GradeThree = gradeThree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getGradeOne() {
        return GradeOne;
    }

    public void setGradeOne(double gradeOne) {
        GradeOne = gradeOne;
    }

    public double getGradeTwo() {
        return GradeTwo;
    }

    public void setGradeTwo(double gradeTwo) {
        GradeTwo = gradeTwo;
    }

    public double getGradeThree() {
        return GradeThree;
    }

    public void setGradeThree(double gradeThree) {
        GradeThree = gradeThree;
    }
}
