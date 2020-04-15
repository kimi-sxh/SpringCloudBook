package com.didispace.web;

/**
 * Created by Administrator on 2016/9/16.
 */
public class User {

    private Long id;
    private String uuid;
    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String uuid, String name, Integer age) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
    }

    public User(Long id, String uuid, String name, Integer age) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
