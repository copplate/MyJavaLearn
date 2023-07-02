import bean.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

//jdbc第二部分，jdbc的练习
public class StatementPrac {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wyc_db01",
                "root","123456");
            Statement stateMent = connection.createStatement()
        ) {
//            int i = stateMent.executeUpdate("update student set name = '花无缺' where sid = 1103");
//            int i = stateMent.executeUpdate("delete from student where sid = 1104");
//            int i = stateMent.executeUpdate("insert into student values (1104,'花无缺','男')");
            ResultSet set = stateMent.executeQuery("select * from student where sid = 00");
            while (set.next()) {
                //每调用一次next()就会向下移动一行，首次调用会移动到第一行
                System.out.println(set.getInt(1) + "  " +
                        set.getString(2) + "  " + set.getNString(3));
                /*Student student = new Student(set.getInt(1), set.getString(2), set.getNString(3));
                student.say();*/
                Student student = convert(set, Student.class);
                student.say();

            }
//            System.out.println("生效了" + i + "行");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //使用反射方法，来让转为类的这段代码更通用
    private static <T> T convert(ResultSet set, Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor(clazz.getConstructors()[0].getParameterTypes());//默认获取第一个构造方法
            Class<?>[] param = constructor.getParameterTypes();//获取参数列表
            Object[] object = new Object[param.length];//存放参数
            for (int i = 0; i < param.length; i++) {//是从1开始的
                object[i] = set.getObject(i + 1);
                if (object[i].getClass() != param[i]) {
                    throw new SQLException("错误的类型转换" + object[i].getClass() + "- >" + param[i]);
                }
            }
            return constructor.newInstance(object);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}