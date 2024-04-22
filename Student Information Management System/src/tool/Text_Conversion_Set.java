package tool;

import Encryption_and_Decryption.SymmetricEncryptionExample;
import pojo.Student;
import utils.getStudents;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Text_Conversion_Set {

    //加入加密类
    SymmetricEncryptionExample sym=new SymmetricEncryptionExample();
    List<Student> list = new ArrayList<>();

    //从文本中读取学生信息存储到集合中
    public List<Student> toList() {
        try (
                Reader re = new FileReader("D:\\Java_project\\学生信息管理系统\\Student Information Management System\\src\\学生\\学生.txt");
                BufferedReader br = new BufferedReader(re);
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                Student student = getStudents.getStudent(sym.Decryption(line));
                list.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //从集合中获取学生对象写入到文本里
    public void toTxT(List<Student> list)
    {
        //删除原有文件并重新创建修改后的学生.txt
        File file=new File("D:\\Java_project\\学生信息管理系统\\Student Information Management System\\src\\学生\\学生.txt");

        try (
               Writer writer = new FileWriter("D:\\Java_project\\学生信息管理系统\\Student Information Management System\\src\\学生\\学生.txt");
                BufferedWriter bw = new BufferedWriter(writer);
        ) {
            Iterator<Student> it= list.iterator();
            while (it.hasNext())
            {
                Student student=it.next();
                String line=student.toString();
                bw.write(sym.Encryption(line));
                bw.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
