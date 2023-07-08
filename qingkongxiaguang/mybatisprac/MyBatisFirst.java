package mybatisprac;

import bean.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class MyBatisFirst {
    public static void main(String[] args) throws FileNotFoundException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().
                build(new FileInputStream("mybatis-config.xml"));
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            List<Student> students = sqlSession.selectList("selectStudent");
            students.forEach(System.out::println);
        }

    }
}
