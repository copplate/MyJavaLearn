package com.qingkongxiaguangweb.jdbcprac;

import java.sql.*;

/**
*jdbc第一部分练习，连接到数据库
*/
public class ConnectSql {
    public static void main(String[] args) {
        //1、通过DriverManager来获得数据库连接
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wyc_db01",
                "root", "123");
             //2、创建一个用于执行SQL的Statement对象
             Statement statement = connection.createStatement()
        ) {//注意前两步都放在try中，因为在最后需要释放资源！
            //3、执行SQL语句，并得到结果集

            ResultSet set = statement.executeQuery("select * from student");
            //4、查看结果
            //下标从1开始
            while (set.next()) {
                System.out.println(set.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //5、释放资源，try-with-resource语法会自动帮我们close
    }
}
