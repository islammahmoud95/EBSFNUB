package com.example.islammahoud.ebsfnub.Data;

import java.io.Serializable;

/**
 * Created by islam mahoud on 10/8/2017.
 */

public class News implements Serializable {
    String title;
    String desc;
    String image;


 public News(){}
    public News(String title,String desc,String image)
    {
        this.title=title;
        this.desc=desc;
        this.image=image;
    }
    public String getTitle(){return title;}
    public void setTitile(String title){this.title=title;}

    public String getDesc(){return desc;}
    public void setDesc(String desc){this.desc=desc;}

    public String getImage(){return image;}
    public void setImage(String image){this.image=image;}



}
