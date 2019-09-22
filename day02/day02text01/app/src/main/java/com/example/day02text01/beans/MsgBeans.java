package com.example.day02text01.beans;

public class MsgBeans {

    private int id;
    private String img;
    private String name;
    private String title;

    public MsgBeans(int id, String img, String name, String title) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
