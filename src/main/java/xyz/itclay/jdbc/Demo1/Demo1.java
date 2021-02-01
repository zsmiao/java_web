package xyz.itclay.jdbc.Demo1;

import java.sql.*;
import java.util.Scanner;

/**
 * @author ZhangSenmiao
 * @date 2021/2/1 17:04
 **/
public class Demo1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://www.mysql.com:3306/managersystem", "root", "123456");
        String sql = "select * from tb_user where username=? and password=? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("数据库连接成功！");
        System.out.println("请输入用户名:");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            System.out.println("登录成功！");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String tel = resultSet.getString("tel");
            System.out.println("您的个人信息是：");
            System.out.println( "name：" + name + "  username：" + username + "  email：" + email + "  tel：" + tel);
        }else {
            System.out.println("登录失败！");
        }
        resultSet.close();
        statement.close();
        conn.close();
    }
}
