package com.example.islammahoud.ebsfnub.Firebaseconnemction;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.islammahoud.ebsfnub.Data.Comitti;
import com.example.islammahoud.ebsfnub.Data.Member;
import com.example.islammahoud.ebsfnub.Data.News;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Created by Windows on 30-01-2015.
 */
public class Savingdata extends Application {


    private static Savingdata sInstance;
    private static Context VV;
    ArrayList<News> news;
    ArrayList<Member> members;
    ArrayList<Comitti> comittis;

    // private static DBMovies mDatabase;
    public Savingdata(Context context) {
        VV = context;


    }

    public static synchronized Savingdata getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Savingdata(context);
        }
        return sInstance;
    }





    public static Savingdata getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }


    public void savenewsdata(ArrayList<News> news) {

        File folder = VV.getExternalFilesDir("Newsdata");
        File newsfolder= new File(folder, "news_data.txt");

        FileOutputStream fs= null;
        try {
             fs = new FileOutputStream(newsfolder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(news);
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public ArrayList<Comitti> getcomdata() {
        ArrayList<Comitti> data = new ArrayList<>();
        if (comittis == null) {
            File folder = VV.getExternalFilesDir("comdata");
            File newsfolder= new File(folder, "com_data.txt");
            ObjectInputStream ois =
                    null;
            try {
                ois = new ObjectInputStream(new FileInputStream(newsfolder));
                try {
                    data = ((ArrayList<Comitti>) ois.readObject());
                    ois.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                L.m("file not created");
            }


            if (data != null) {
                L.m("data retrieved from file " + data.size());
                comittis = data;
            }
        }
        return comittis;
    }
    public ArrayList<Comitti> getcombynum(int num) {
        String title;
        if (comittis == null)
            getcomdata();
        ArrayList<Comitti> wantedSessions = new ArrayList<>();
        String dayNumber = String.valueOf(num);
        String ses_day;
        if(comittis !=null){
            int size = comittis.size();
            L.m("ss"+size);
            for (int i = 0; i < size; i++) {
                L.m("i "+i);
                ses_day = comittis.get(i).getNum();
                if (ses_day.equals(dayNumber)) {
                    wantedSessions.add(comittis.get(i));
                     title=comittis.get(i).getName();
                }

                else
                    continue;}
        }

        return wantedSessions;
    }
    public String gettitleynum(int num) {

        if (comittis == null)
            getcomdata();
        ArrayList<Comitti> wantedSessions = new ArrayList<>();
        String title="spam";
        String dayNumber = String.valueOf(num);
        String ses_day;
        if(comittis !=null){

            int size = comittis.size();
            L.m("ss"+size);
            for (int i = 0; i < size; i++) {
                L.m("i "+i);
                ses_day = comittis.get(i).getNum();
                if (ses_day.equals(dayNumber)) {
                    wantedSessions.add(comittis.get(i));
                      title=comittis.get(i).getName();
                }

                else
                    continue;}
        }

        return title;
    }


    public void savecomdata(ArrayList<Comitti> com) {

        File folder = VV.getExternalFilesDir("comdata");
        File newsfolder= new File(folder, "com_data.txt");
        FileOutputStream fs= null;
        try {
            fs = new FileOutputStream(newsfolder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(com);
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<News> getnewsdata() {
        ArrayList<News> data = new ArrayList<>();
        if (news == null) {
            File folder = VV.getExternalFilesDir("Newsdata");
            File newsfolder= new File(folder, "news_data.txt");
            ObjectInputStream ois =
                    null;
            try {
                ois = new ObjectInputStream(new FileInputStream(newsfolder));
                try {
                    data = ((ArrayList<News>) ois.readObject());
                    ois.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                L.m("file not created");
            }


            if (data != null) {
                L.m("data retrieved from file " + data.size());
                news = data;
            }
        }
        return news;
    }

    public void savememberdata(ArrayList<Member> members) {

        File folder = VV.getExternalFilesDir("memeberdata");
        File newsfolder= new File(folder, "Member_data.txt");

        FileOutputStream fs= null;
        try {
            fs = new FileOutputStream(newsfolder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(members);
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Member> getmemberdata() {
        ArrayList<Member> data = new ArrayList<>();
        if (members == null) {
            File folder = VV.getExternalFilesDir("memeberdata");
            File newsfolder= new File(folder, "Member_data.txt");
            ObjectInputStream ois =
                    null;
            try {
                ois = new ObjectInputStream(new FileInputStream(newsfolder));
                try {
                    data = ((ArrayList<Member>) ois.readObject());
                    ois.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                L.m("file not created");
            }


            if (data != null) {
                L.m("data retrieved from file " + data.size());
                members = data;
            }
        }
        return members;
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static void saveToPreferences(Context context, String preferenceName, boolean preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    public static boolean readFromPreferences(Context context, String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }



}
