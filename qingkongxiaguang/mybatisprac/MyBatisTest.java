package com.qingkongxiaguangweb.mybatis_;

import com.qingkongxiaguangweb.mybatis_.entity.Student;
import com.qingkongxiaguangweb.mybatis_.mapper.TestMapper;
import com.qingkongxiaguangweb.mybatis_.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class MyBatisTest {
    public static void main(String[] args) throws FileNotFoundException {
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream("mybatis-config.xml"));
//        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            /*List<Student> students = sqlSession.selectList("selectStudent");
            students.forEach(System.out::println);*/
//            System.out.println((Student)sqlSession.selectOne("selectStudent",111));

            //定义接口之前，预制提供的这个是不明确的，定义接口之后，操作得到的结果是什么就很明确了
            TestMapper mapper = sqlSession.getMapper(TestMapper.class);//通过getMapper()方法获取接口的实现类
            /*
            System.out.println(mapper.addStudent(new Student()
                    //.setStudentId(114)
                    .setName("刘能").setSex("男")));
                    */
            mapper.selectStudent().forEach(System.out::println);
            System.out.println(mapper.getTeacherByTid(11));
        }
    }
}
