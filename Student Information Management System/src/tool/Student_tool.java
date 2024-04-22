package tool;

import pojo.Student;
import utils.getStudents;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Student_tool {
    public void addStudent(List<Student> list) {

        while (true) {
            //添加学生方法实现
            System.out.println("请输入学生的信息(中间用，分割)：姓名，学号，性别，学生卡余额，和语，数，外三门课的成绩");
            System.out.println("输入end结束添加");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if (!s.equalsIgnoreCase("end")) {
                //把字符串转换成学生对象
                Student student = getStudents.getStudent(s);
                //判断是否重复
                if (!list.contains(student)) {
                    //把改学生对象添加到集合中
                    list.add(student);
                } else {
                    System.out.println("该学生已经被添加过，请重新输入！");
                }
            } else {
                break;
            }
        }
    }

    public void delete(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("学生信息不存在！请添加学生！");
        } else {

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("输入1提供姓名删除,输入2提供按学号删除");
                int num1 = sc.nextInt();
                if (num1 == 1) {
                    deleteByName(list);
                    break;
                } else if (num1 == 2) {
                    deleteById(list);
                    break;
                } else {
                    System.out.println("输入内容错误！请重新输入！！！");
                }
            }
        }
    }

    public void deleteByName(List<Student> list) {
        // 根据学生姓名删除学生的方法实现
        while (true) {
            System.out.println("请输入要删除学生的姓名：");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();

            if (!name.equalsIgnoreCase("end")) {
                // 根据学生Id删除学生的方法实现
                //判断是否找到该Id
                boolean find = false;
                for (Student s : list) {
                    if (s.getName().equals(name)) {
                        list.remove(s);
                        find = true;
                        System.out.println("删除成功！");
                        break;
                    }
                }
                if (!find) {
                    System.out.println("输入的姓名不存在，删除失败！请重新输入");
                }
            } else {
                break;
            }
        }


    }

    public void deleteById(List<Student> list) {
        // 根据学生Id删除学生的方法实现
        while (true) {
            System.out.println("请输入要删除学生的Id：");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();

            if (!id.equalsIgnoreCase("end")) {
                // 根据学生Id删除学生的方法实现
                //判断是否找到该Id
                boolean find = false;
                for (Student s : list) {
                    if (s.getID().equals(id)) {
                        list.remove(s);
                        find = true;
                        System.out.println("删除成功！");
                        break;
                    }
                }
                if (!find) {
                    System.out.println("输入的Id不存在，删除失败！请重新输入");
                }
            } else {
                break;
            }
        }


    }

    public void updateStudent(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("学生信息不存在！请添加学生！");
        } else {
            if (list.isEmpty()) {
                System.out.println("学生信息不存在！请添加学生！");
            } else {
                // 更新学生信息的方法实现
                System.out.println("请输入要更新学生信息的学生的Id：");
                Scanner sc = new Scanner(System.in);
                String id = sc.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    Student s = list.get(i);
                    if (s.getID().equals(id)) {
                        System.out.println("请输入更新该学生的信息(中间用，分割)：姓名，学号，性别，学生卡余额，和语，数，外三门课的成绩\"：");
                        String line = sc.nextLine();
                        //把输入的信息转换成学生对象
                        Student student = getStudents.getStudent(line);
                        list.set(i, student);
                        System.out.println("更新成功！");
                        break;
                    }
                }
            }
        }

    }

    public void searchStudent(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("学生信息不存在！请添加学生！");
        } else {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("输入1提供查询全部学生信息");
                System.out.println("输入2提供根据学生姓名和id查询的方法");
                int num1 = sc.nextInt();
                if (num1 == 1) {
                    findAllStudent(list);
                    break;
                } else if (num1 == 2) {
                    findOneStudent(list);
                    break;
                } else {
                    System.out.println("输入内容错误！请重新输入！！！");
                }
            }
        }

    }

    //查询单个学生(可能重名)
    public void findOneStudent(List<Student> list) {

        // 根据学生ID查找学生的方法实现
        //1. 首先询问查询方式
        //2. 提供根据学生姓名查询的方法
        //3. 提供根据学生学号查询的方法
        System.out.println("根据姓名查询输入1，根据学号查询输入2,退出输入end");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("end"))
            {
                break;
            }
            if (line.equals("1")) {
                while (true) {

                    System.out.println("请输入姓名：");
                    String name = sc.nextLine();
                    //判断是否查找到该学生信息
                    boolean find = false;
                    for (Student s : list) {
                        if (s.getName().equals(name)) {
                            find = true;
                            System.out.println(s.toString());
                            //名字可能有重复，需要遍历完集合
                        }
                    }
                    if (find) {
                        break;
                    }
                    System.out.println("未查询到该学生的信息，请重新输入该学生的姓名！");

                }

            } else if (line.equals("2")) {
                while (true) {

                    System.out.println("请输入学生Id：");
                    String id = sc.nextLine();
                    //判断是否查找到该学生信息
                    boolean find = false;
                    for (Student s : list) {
                        if (s.getID().equals(id)) {
                            find = true;
                            System.out.println(s.toString());
                            //ID默认不会有重复
                            break;
                        }
                    }
                    if (find) {
                        break;
                    }
                    System.out.println("未查询到该学生的信息，请重新输入该学生的Id！");

                }
            } else {
                System.out.println("输入不合法，请重新输入！");
            }


        }

    }

    //查询所有学生信息
    public void findAllStudent(List<Student> list) {

        System.out.println("所有学生的信息");
        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            Student student = it.next();
            System.out.println(student.toString());

        }

    }

    //求取平均分
    public void getAveScore(List<Student> list) {
        if (list.isEmpty()) {
            System.out.println("学生信息不存在！请添加学生！");
        } else {
            System.out.println("查询哪门课的平均分(输入1,2,3,分别代表三门课)：");
            Scanner sc = new Scanner(System.in);
            double sum1 = 0;
            double sum2 = 0;
            double sum3 = 0;

            for (Student s : list) {
                sum1 += s.getGradeOne();
                sum2 += s.getGradeTwo();
                sum3 += s.getGradeThree();
            }

            while (true) {
                System.out.println("请输入查询课的代号(输入end退出):");
                String num = sc.nextLine();
                if (num.equalsIgnoreCase("end"))
                {
                    break;
                }else
                {
                    if (num.equals("1")) {
                        System.out.println("第一门的平均分是：" + (sum1 / (double) list.size()));

                    } else if (num.equals("2")) {
                        System.out.println("第二门的平均分是：" + (sum2 / (double) list.size()));

                    } else if (num.equals("3")) {
                        System.out.println("第三门的平均分是：" + (sum3 / (double) list.size()));

                    } else {
                        System.out.println("输入不合法，请重新输入！");
                    }
                }

            }
        }
    }

}


