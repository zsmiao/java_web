package xyz.itclay.jdbc.Demo2;

import org.junit.jupiter.api.Test;
import xyz.itclay.jdbc.domain.User;
import xyz.itclay.jdbc.utils.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author ZhangSenmiao
 * @date 2021/2/1 18:48
 **/

public class Demo2Test {
    /**
     * 修改信息用户密码
     */
    @Test
    public void testUpdate() throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        String name="张三";
        String password="123";
        String sql="update tb_user set password=? where name=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(2,name);
        statement.setString(1,password);
        int row = statement.executeUpdate();
        if (row>0){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
        JdbcUtil.close(connection,statement);
    }

    /**
     * 查询所有
     *
     * @throws Exception
     */
    @Test
    public void testFindAll() throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "select * from tb_user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            User user = null;
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String tel = resultSet.getString("tel");
            user = new User(id, name, username, password, email, tel);
            list.add(user);
        }
        for (User user : list) {
            String s = user.toString();
            System.out.println(s);
        }
        JdbcUtil.close(connection, statement, resultSet);
    }
}
