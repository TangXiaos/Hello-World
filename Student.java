package cn.partfive;

/**
 * Created by Tangs on 2018/5/24.
 */
public class Student {
    private long id;
    private String name;
    private int os;
    private int java;
    private int math;

    public Student() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setJava(int java) {
        this.java = java;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOs() {
        return os;
    }

    public int getMath() {
        return math;
    }

    public int getJava() {
        return java;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
