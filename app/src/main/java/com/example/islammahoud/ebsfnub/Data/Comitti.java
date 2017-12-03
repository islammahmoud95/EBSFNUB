package com.example.islammahoud.ebsfnub.Data;

import java.io.Serializable;

/**
 * Created by islam mahoud on 10/8/2017.
 */

public class Comitti  implements Serializable {


    String name;
    String desc;
    String num;

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getDesc(){return desc;}
    public void setDesc(String desc){this.desc=desc;}

    public String getNum(){return num;}
    public void setNum(String num){this.num=num;}
}