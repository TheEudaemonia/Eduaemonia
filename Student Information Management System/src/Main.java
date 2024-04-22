import Administrator_verification.check;
import pojo.Student;
import tool.Administer_tool;
import tool.Student_tool;
import tool.Text_Conversion_Set;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {

            //登录页面
            check c = new check();
            c.check_msg();

            //从文本中读取学生信息的集合
            List<Student> list = new ArrayList<>();
            Text_Conversion_Set textConversionSet = new Text_Conversion_Set();
            list = textConversionSet.toList();

            //学生信息操作类
            Student_tool studentTool = new Student_tool();

            //管理员信息操作类
            Administer_tool administerTool = new Administer_tool();

            if (list.isEmpty()) {
                System.out.println("学生列表为空！请添加学生！");
            }

            Scanner sc = new Scanner(System.in);

            /**
             * 这里设计变量来配置二级页面，利用改变该变量的值达到退出二级界面的效果
             */

            boolean Second = true;
            while (Second) {

                /**
                 * 这里要再次从文本中读取学生信息添加到了集合中，避免最先没有学生添加到集合中时
                 * 添加了学生到新集合中未被读取的情况
                 */
                if (list.isEmpty()) {
                    list = textConversionSet.toList();
                }
                System.out.println("**********欢迎进入学生信息管理系统**********");
                System.out.println("请输入你想进行的操作：");
                System.out.println("1.添加学生信息");
                System.out.println("2.删除学生信息");
                System.out.println("3.修改学生信息");
                System.out.println("4.查询学生信息");
                System.out.println("5.求取平均分");
                System.out.println("6.添加管理员");
                System.out.println("7.删除管理员");
                System.out.println("8.退出");

                /**
                 * 因为在程序输入end结束后才能把经过修改的数据重新写入覆盖到原文件中，所以在这
                 * 级页面不能出错，这里可能因为输入非数字引发程序崩溃，这里用try-catch来捕获异常
                 */

                boolean isValidInput=false;
                int num=0;
                while (!isValidInput)
                {
                    try{
                        num = sc.nextInt();
                        isValidInput=true;
                    }catch(InputMismatchException e) {
                        System.out.println("输入不是有效的整数，请重新输入：");
                        sc.next(); // 清除非整数输入
                    }

                }


                if (num < 1 ||num > 8) {
                    System.out.println("输入不符合要求，请重新输入！");
                    continue;
                }

                switch (num) {
                    case 1:
                        studentTool.addStudent(list);
                        break;
                    case 2:
                        studentTool.delete(list);
                        break;
                    case 3:
                        studentTool.updateStudent(list);
                        break;
                    case 4:
                        studentTool.searchStudent(list);
                        break;
                    case 5:
                        studentTool.getAveScore(list);
                        break;
                    case 6:
                        administerTool.add_Administer();
                        break;
                    case 7:
                        administerTool.remove_Adm();
                        break;
                    case 8:
                        while (true) {
                            System.out.println("确认退出请输入end");
                            /**
                             * 这里要防止sc读取到前一行的换行符
                             */
                            sc.nextLine();
                            String line = sc.nextLine();
                            if (line.equalsIgnoreCase("end")) {
                                Second = false;
                                /**
                                 * 在退出前要把集合中的学生对象写入txt中
                                 */
                                textConversionSet.toTxT(list);
                                break;
                            }else
                            {
                                System.out.println("输入不合法,请重新输入！");
                            }
                        }
                        break;
                }
            }
        }
    }
}