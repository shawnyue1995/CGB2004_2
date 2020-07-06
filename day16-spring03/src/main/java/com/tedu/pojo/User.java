package com.tedu.pojo;

public class User {
    private String name;
    private Integer age;
    private UserInfo info;

    public User() {
    }

    public User(String name, Integer age, UserInfo info) {
        this.name = name;
        this.age = age;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName方法执行了,为name属性赋值为:"+name);
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("setAge方法执行了,为age属性赋值为:"+age);
        this.age = age;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        System.out.println("setInfo方法执行了,为info属性赋值为:"+info);
        this.info = info;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", info=" + info +
                '}';
    }
}
