package com.example.islammahoud.ebsfnub.Firebaseconnemction;

import com.example.islammahoud.ebsfnub.Data.Comitti;
import com.example.islammahoud.ebsfnub.Data.Member;
import com.example.islammahoud.ebsfnub.Data.News;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by islam mahoud on 10/11/2017.
 */

public class Firebaseconnection {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mstorageref;
    private StorageReference mChatPhotosStorageReference;
    ArrayList<News> news=new ArrayList<>();
    ArrayList<Member> members=new ArrayList<>();
    ArrayList<Comitti> comittis =new ArrayList<>();
   public void getnewsdata() {

       mFirebaseDatabase = FirebaseDatabase.getInstance();

       mFirebaseStorage = FirebaseStorage.getInstance();

       mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("news");
       mstorageref =mFirebaseStorage.getReference().child("Newsimage");
       Query query=mMessagesDatabaseReference.orderByChild("time");
       query.keepSynced(true);
       query.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
       query.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               savenewsdata(dataSnapshot);

           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               savenewsdata(dataSnapshot);
           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });


   }

    private void savenewsdata(DataSnapshot snapshot) {
        L.m("key "+snapshot.getKey());
       news.add(snapshot.getValue(News.class));
        Savingdata.getInstance().savenewsdata(news);

    }


    public void getcomdata() {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference =  mFirebaseDatabase.getReference().child("comittee");
mChildEventListener=new ChildEventListener() {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        savecomdata(dataSnapshot);

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        savecomdata(dataSnapshot);

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {


    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
};
        mMessagesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                savecomdata(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void savecomdata(DataSnapshot snapshot) {
        int size= (int) snapshot.getChildrenCount();
        ArrayList<Comitti> com =new ArrayList<>(size);
        Comitti ses;
        for (int i=0;i<size;i++)
        {
       
            ses= snapshot.child(String.valueOf(i)).getValue(Comitti.class);
            com.add(ses);
        }
        Savingdata.getInstance().savecomdata(com);


    }

    public void getmemberdata() {

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mFirebaseStorage = FirebaseStorage.getInstance();
      //  mFirebaseDatabase.getInstance().getReference().child("member").orderByChild("order");
        mMessagesDatabaseReference= mFirebaseDatabase.getReference().child("member");
        Query query=mMessagesDatabaseReference.orderByChild("order");
        mstorageref =mFirebaseStorage.getReference().child("Memberimage");
        query.keepSynced(true);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                savememberdata(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                savememberdata(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    private void savememberdata(DataSnapshot snapshot) {
        L.m("key "+snapshot.getKey());
        members.add(snapshot.getValue(Member.class));
        Savingdata.getInstance().savememberdata(members);

    }
    public void excuteRequestes() {
        getnewsdata();
        getcomdata();
        getmemberdata();
    }
}
