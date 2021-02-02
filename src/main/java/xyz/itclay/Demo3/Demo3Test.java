package xyz.itclay.Demo3;

import org.junit.jupiter.api.Test;
import xyz.itclay.jdbc.domain.User;
import xyz.itclay.jdbc.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试插入数据
 *
 * @author ZhangSenmiao
 * @date 2021/2/2 9:12
 **/
public class Demo3Test {
    @Test
    public void testSave() throws SQLException {
        User user = new User();
        user.setName("钱六");
        user.setUsername("ql");
        user.setPassword("ql");
        user.setEmail("ql@outlook.com");
        user.setTel("19800000000");

        Connection connection = JdbcUtil.getConnection();
        String sql = "insert into tb_user values (null,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getTel());
        int row = statement.executeUpdate();
        if (row > 0) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败！");
        }
        JdbcUtil.close(connection, statement);
    }
}
