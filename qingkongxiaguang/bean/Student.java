package bean;

public class Student {
    Integer sid;
    String name;
    String sex;

    public Student(Integer sid, String name, String sex) {
        this.sid = sid;
        this.name = name;
        this.sex = sex;
    }

    public void say() {
        System.out.println(sid + name + sex);
    }
}
