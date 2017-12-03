package com.example.islammahoud.ebsfnub.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.islammahoud.ebsfnub.Admins.Loginactivity;
import com.example.islammahoud.ebsfnub.Data.Feed;
import com.example.islammahoud.ebsfnub.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.

 */
public class Feed_fragment extends Fragment {
    private EditText editText;
    private Button btn;
    private FirebaseAuth auth;
    private FirebaseDatabase mfirebase;
    private DatabaseReference mreferance;
    private CallbackManager callbackmanager;
    private Profile profile;
    private ProfileTracker profileTracker;
    private AccessTokenTracker token;
    private int count;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        auth=FirebaseAuth.getInstance();

       View view=  inflater.inflate(R.layout.fragment_feed_fragment, container, false);
        mfirebase=FirebaseDatabase.getInstance();
        mreferance=mfirebase.getReference().child("feed");
        mreferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count= 0- (int)dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        editText=(EditText)view.findViewById(R.id.efeedbacktext);
        btn=view.findViewById(R.id.feedback_submit);
        callbackmanager=CallbackManager.Factory.create();
        profile= Profile.getCurrentProfile();
        token=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

profile=currentProfile;
            }
        };
        token.startTracking();
        profileTracker.startTracking();


            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation())
                {
                    if (profile == null) {
                        Toast.makeText(getContext(), "Please Login first", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), Loginactivity.class);
                        startActivity(intent);
                    } else {


                        Feed feed = new Feed(count,editText.getText().toString(), profile.getName().toString(),profile.getLinkUri().toString(),profile.getProfilePictureUri(200,200).toString());
                        mreferance.push().setValue(feed);
                        editText.setText("");
                    }
                }
            }
        });

        return view;
    }

    private boolean validation() {
        String titletext = editText.getText().toString();


        if (TextUtils.isEmpty(titletext) ){
            editText.setError("Required");

            return false;
        }
        return true;
    }
}
