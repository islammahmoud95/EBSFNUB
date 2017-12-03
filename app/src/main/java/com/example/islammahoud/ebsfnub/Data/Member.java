package com.example.islammahoud.ebsfnub.Data;

import java.io.Serializable;

/**
 * Created by islam mahoud on 10/8/2017.
 */

public class Member implements Serializable {
    String name;
    String job;
    String image;
    String order;
    public Member() {
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
