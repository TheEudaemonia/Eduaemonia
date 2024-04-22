package tool;

import Administrator_verification.check;
import Encryption_and_Decryption.SymmetricEncryptionExample;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Administer_tool {
    SymmetricEncryptionExample sym = new SymmetricEncryptionExample();


    //添加管理员
    public void add_Administer() {
        Map<String, String> map = new HashMap<>();
        map = return_write_msg_in_map();
        while (true) {
            System.out.println("请输入管理员姓名:");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            //判断管理员的名称是否重复
            if (map.containsKey(name))
            {
                System.out.println("该管理员姓名已经创建！请重新输入！");
                continue;
            }
            System.out.println("请输入管理员密码:");
            String password = sc.nextLine().trim();
            System.out.println("请再次输入管理员密码:");
            String rePassword = sc.nextLine().trim();
            if (!password.equals(rePassword)) {
                System.out.println("两次输入的密码不一致，请重新输入！");
            } else {

                //把获取到的数据写入到管理员.txt中
                try (
                        /**
                         * 这里要设置成true，进行追加
                         */
                        Writer wr = new FileWriter("D:\\Java_project\\学生信息管理系统\\Student Information Management System\\src\\管理员\\管理员.txt", true);
                        BufferedWriter bw = new BufferedWriter(wr);
                ) {
                    String codeName = sym.Encryption(name);
                    String codePassword = sym.Encryption(password);
                    bw.write("管理员名字:" + codeName + "," + "管理员密码:" + codePassword + ";");
                    bw.newLine();
                    System.out.println("添加成功！");
                } catch (Exception e) {
                    System.out.println("添加失败！");
                    throw new RuntimeException(e);
                }
                /**
                 * 这里要添加break用来跳过已经添加成功的循环
                 */
                break;
            }
        }
    }

    //删除管理员
    public void remove_Adm() {
        //在删除管理员之前要从管理员.txt文件中把当前所有管理员的信息存放到map中
        //创建map用来存储解密出的管理员名字和密码
        //key-管理员名字,value-管理员密码
        Map<String, String> map = new HashMap<>();

        map = return_write_msg_in_map();

        while (true) {
            //根据管理员名字删除该管理员的信息
            System.out.println("请输入要删除管理员的姓名：");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            if (map.containsKey(name)) {
                map.remove(name);
                System.out.println("删除成功！");
                break;
            } else {
                System.out.println("删除失败！请重新输入管理员的名字");
            }
        }

        //删除之后把该map集合中的内容重新写入到管理员.txt中
        if (map.isEmpty()) {
            try (
                    Writer wr = new FileWriter("D:\\Java_project\\学生信息管理系统\\Student Information Management System\\src\\管理员\\管理员.txt");
                    BufferedWriter bw = new BufferedWriter(wr);
            ) {
                bw.write("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            for (String name : map.keySet()) {
                String password = map.get(name);

                try (
                        Writer wr = new FileWriter("D:\\Java_project\\学生信息管理系统\\Student Information Management System\\src\\管理员\\管理员.txt");
                        BufferedWriter bw = new BufferedWriter(wr);
                ) {
                    String codeName = sym.Encryption(name);
                    String codePassword = sym.Encryption(password);
                    bw.write("管理员名字:" + codeName + "," + "管理员密码:" + codePassword + ";");
                    bw.newLine();
                    System.out.println("添加成功！");
                } catch (Exception e) {
                    System.out.println("添加失败！");
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //向map集合中存放管理员信息(有返回参数)
    public Map<String, String> return_write_msg_in_map() {
        Map<String, String> map = new HashMap<>();
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

        return map;
    }

}
