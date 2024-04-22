package Administrator_verification;

import Encryption_and_Decryption.SymmetricEncryptionExample;
import tool.Administer_tool;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class check {
    //创建map用来存储解密出的管理员名字和密码
    //key-管理员名字,value-管理员密码
    Map<String, String> map = new HashMap<>();

    //管理员方法类，用于添加管理员方法
    Administer_tool administerTool = new Administer_tool();

    //加密和解密类
    private SymmetricEncryptionExample sym = new SymmetricEncryptionExample();

    public void check_msg() {
        write_msg_in_map();
        if (map.isEmpty()) {
            System.out.println("管理员.txt文件中没有信息！");
            System.out.println("************************");
            System.out.println("请创建管理员信息！");
            administerTool.add_Administer();
        }

        /**
         * 这里要重新把管理员的信息写入到map集合中，是因为在前面如果管理员为空时进行添加管理员
         * 该新添加的管理员不能被读取到map集合中从而进行后续的登录操作
         */
        //System.out.println(map.isEmpty());
        if (map.isEmpty()) {
            write_msg_in_map();
        }
        //System.out.println(map.isEmpty());

        Scanner sc = new Scanner(System.in);
        int error = 5;
        while (true) {
            if (error == 0) {
                System.out.println("输入密码错误次数过多，请5分钟后再试！");
                int SleepTime = 5 * 60;
                //这里要恢复error的值便于重新开始计数
                error = 5;
                while (SleepTime >= 0) {
                    int min = SleepTime / 60;
                    int sec = SleepTime % 60;

                    String msg=String.format("\r在%d分%d秒后可以重新输入密码！", min, sec);
                    /**
                     * 这里是print才会有单行显示的效果，不能用println
                     */
                    System.out.print(msg);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 这里使用\r让光标回到当前行的开头，实现在同一行上的覆盖输出效果。
                     */

                    SleepTime--;
                }

            }

            System.out.println("*********欢迎使用学生信息管理系统*********");
            System.out.println("请输入管理员名字：");
            String name = sc.nextLine();
            if (map.containsKey(name)) {
                System.out.println("请输入管理员密码：");
                System.out.println("当前还有"+error+"次机会");
                String password = sc.nextLine();

                //System.out.println(map.containsKey(name)+map.get(name));

                if (map.get(name).equals(password)) {
                    break;
                }
                error--;
                System.out.println("密码输入错误！");
                continue;
            }
            System.out.println("管理员不存在！");
        }


    }

    //向map集合中存放管理员信息(无参)
    public void write_msg_in_map() {

        try (
                Reader rd = new FileReader("D:\\Java_project\\学生信息管理系统\\Student Information Management System\\src\\管理员\\管理员.txt");
                BufferedReader bf = new BufferedReader(rd);
        ) {
            /**
             * 在这里解析的字符串必须要和写入的字符串类型一致才可以
             */
            Pattern pattern = Pattern.compile("管理员名字:(\\S*),管理员密码:(\\S*);");

            //读取一行信息并进行模式匹配，解析出结果之后存入map集合中
            String line;
            while ((line = bf.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String name = sym.Decryption(matcher.group(1));
                    String password = sym.Decryption(matcher.group(2));

                    //System.out.println("name"+name+"password:"+password);

                    map.put(name, password);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
