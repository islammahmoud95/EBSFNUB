package com.example.islammahoud.ebsfnub.Activties;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islammahoud.ebsfnub.Admins.Loginactivity;
import com.example.islammahoud.ebsfnub.Data.Comitti;
import com.example.islammahoud.ebsfnub.Data.Newsadapter;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.Firebaseconnection;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.L;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.Savingdata;
import com.example.islammahoud.ebsfnub.Fragments.Chairperson;
import com.example.islammahoud.ebsfnub.Fragments.ComitteeFragment;
import com.example.islammahoud.ebsfnub.Fragments.Feed_fragment;
import com.example.islammahoud.ebsfnub.Fragments.NewsFragment;
import com.example.islammahoud.ebsfnub.Fragments.MainFragment;
import com.example.islammahoud.ebsfnub.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
//import com.firebase.client.Firebase;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MaterialTabListener {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosStorageReference;
    private Newsadapter mMessageAdapter;
    private FrameLayout frameLayout;
    private CallbackManager mcallmanager;
    private AccessTokenTracker token;
    private Profile profile;
    private ProfileTracker profileTracker;
    private ImageView improfile;
    private TextView user_name;
    private MaterialTabHost mTabHost;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;
    private AppBarLayout mAppBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Savingdata.getInstance(this);
        new Firebaseconnection().excuteRequestes();
      setupTabs();
        getSupportFragmentManager().beginTransaction().add(R.id.container,new NewsFragment()).commit();
        mTabHost.setVisibility(View.GONE);
        mPager.setVisibility(View.GONE);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        improfile=(ImageView) navigationView.getHeaderView(0).findViewById(R.id.improfile);
        user_name=(TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name);

        mcallmanager=CallbackManager.Factory.create();
        profile=Profile.getCurrentProfile();
        makeprofile(profile);
        token=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                makeprofile(currentProfile);

            }
        };
        token.startTracking();
        profileTracker.startTracking();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mAppBarLayout= (AppBarLayout) findViewById(R.id.app_par);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallmanager.onActivityResult(requestCode, resultCode, data);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.news) {
            setTitle("News");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new NewsFragment()).commit();
            mTabHost.setVisibility(View.GONE);
            mPager.setVisibility(View.GONE);


        } else if (id == R.id.event) {
            Toast.makeText(this,"Coming soon",Toast.LENGTH_LONG).show();



        } else if (id == R.id.cahirperson) {
            setTitle("Chairs Persons");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new Chairperson()).commit();
            mTabHost.setVisibility(View.GONE);
            mPager.setVisibility(View.GONE);


        } else if (id == R.id.ourcomittee) {
            setTitle("Our Committee");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new MainFragment()).commit();
            mPager.setVisibility(View.VISIBLE);
            mTabHost.setVisibility(View.VISIBLE);
        } else if (id == R.id.feedback) {
            setTitle("Feed back");
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new Feed_fragment()).commit();
            mTabHost.setVisibility(View.GONE);
            mPager.setVisibility(View.GONE);

        } else if (id == R.id.login) {

          Intent intent=new Intent(this, Loginactivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void makeprofile(Profile profile){
        if(profile != null){
            Picasso.with(getApplicationContext()).load(profile.getProfilePictureUri(150,150)).into(improfile);
            user_name.setText(profile.getFirstName()+" "+profile.getLastName() );

        }
    }

    //mtabhostsetup
    public  void setupTabs() {
        mTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        mPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        //when the page changes in the ViewPager, update the Tabs accordingly
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabHost.setSelectedNavigationItem(position);

            }
        });
        //Add all the Tabs to the TabHost
        for (int i = 0; i < mAdapter.getCount(); i++) {
            mTabHost.addTab(
                    mTabHost.newTab()
                            .setText(Savingdata.getInstance().gettitleynum(i))
                            // .setIcon(mAdapter.getIcon(i))
                            .setTabListener(this));
        }
    }
    @Override
    public void onTabSelected(MaterialTab tab) {
        mPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        // int icons[]={R.drawable.icon_calendar,R.drawable.icon_speaker,R.drawable.sponsor2,R.drawable.icon_feedback,R.drawable.aboutus,R.drawable.aboutus};
        //ArrayList<Comitti> titles=new ArrayList<>();
        String[] titles = {"Day 1","Day 2","Day 3","Day 4","Day 5","Day 6","Workshops"};

        FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        public Fragment getItem(int num) {
            Fragment fragment = null;
            ArrayList<Comitti> com=new ArrayList<>();
            int size=com.size();
            L.m("getItem called for " + num);

    switch (num) {
        case 0:
            fragment = ComitteeFragment.newInstance(0);
            break;
        case 1:
            fragment = ComitteeFragment.newInstance(1);
            break;
        case 2:
            fragment = ComitteeFragment.newInstance(2);
            break;
        case 3:
            fragment = ComitteeFragment.newInstance(3);
            break;
        case 4:
            fragment = ComitteeFragment.newInstance(4);
            break;
        case 5:
            fragment = ComitteeFragment.newInstance(5);
            break;
        case 6:
            fragment = ComitteeFragment.newInstance(6);
            //  getSupportFragmentManager().beginTransaction().replace(R.id.container, new LecturesFragment()).commit();

            break;
        case 7:
            fragment = ComitteeFragment.newInstance(7);
            break;
        case 8:
            fragment = ComitteeFragment.newInstance(8);
            break;
        case 9:
            fragment = ComitteeFragment.newInstance(9);
            break;
        case 10:
            fragment = ComitteeFragment.newInstance(10);
            break;
        case 11:
            fragment = ComitteeFragment.newInstance(11);
            break;

}
            return fragment;

        }

        @Override
        public int getCount() {
            return 12;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];

        }

      /*  private Drawable getIcon(int position) {
            return getResources().getDrawable(icons[position]);
        }*/
    }
}
