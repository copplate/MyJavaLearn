package com.qingkongxiaguangweb.mybatis_.mapper;


import com.qingkongxiaguangweb.mybatis_.entity.Student;
import com.qingkongxiaguangweb.mybatis_.entity.Teacher;

import java.util.List;

public interface TestMapper {
    List<Student> selectStudent();//那么怎么让Mybatis知道这个接口和Mapper是关联的？直接把namespace改成接口所在的位置就行了

    Student getStudentBySid(int sid);

    int addStudent(Student student);

    Teacher getTeacherByTid(int tid);
}
