package com.example.islammahoud.ebsfnub.Data;

/**
 * Created by islam mahoud on 10/8/2017.
 */

public class Feed {
    int uid;
    String user;
     String feedback;
    String uri;
    String photo;

    public Feed( int uid,String feedback,String user,String uri,String photo)
    {
        this.uid=uid;
        this.feedback=feedback;
        this.user=user;
        this.uri=uri;
        this.photo=photo;
    }

    public String getFeedback(){return feedback;}
    public void setFeedback(String feedback){this.feedback=feedback;}

    public String getUser(){return user;}
    public void setUser(String user){this.user = user;}

    public String getUri(){return uri;}
    public void setUri(String uri){this.uri = uri;}

    public String getPhoto(){return photo;}
    public void setPhoto(String photo){this.photo = photo;}

    public int getUid(){return uid;}
    public void setUid(int uid){this.uid = uid;}

}
