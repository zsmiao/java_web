package xyz.itclay.jdbc.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * jdbc工具类
 * 工具无需创建对象
 * static静态方法
 *
 * @author ZhangSenmiao
 * @date 2021/2/1 18:30
 **/
public class JdbcUtil {
    private static Connection connection;

    private JdbcUtil() {
    }

    static {
        try {
            ResourceBundle db = ResourceBundle.getBundle("db");
            Class.forName(db.getString("driver"));
            connection = DriverManager.getConnection(db.getString("url"), db.getString("username"), db.getString("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

    /**
     * 释放资源
     *
     * @author ZhangSenmiao
     * @date 2021/2/1 18:42
     **/
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }
}
