package utils;

import pojo.Student;

import java.util.Scanner;

public class getStudents {
    //将输入的字符串转换为学生对象
    public static Student getStudent(String s) {
        /**
         * 由于从文本中解析出来的字符串最后有一个;，这会使得最后一项成绩无法解析为double字符串
         * 这里要先去除行末的分好
         */
        if (s.endsWith(";"))
        {
            s=s.substring(0,s.length()-1);
        }
        //解析字符串
        String[] parts = s.split(",");
        String name = parts[0].trim();
        String ID = parts[1].trim();
        String sex = parts[2].trim();
        double balance = Double.parseDouble(parts[3].trim());
        double GradeOne = Double.parseDouble(parts[4].trim());
        double GradeTwo = Double.parseDouble(parts[5].trim());
        double GradeThree = Double.parseDouble(parts[6].trim());

        Student student = new Student(name, ID, sex, balance, GradeOne, GradeTwo, GradeThree);
        return student;
    }
}
