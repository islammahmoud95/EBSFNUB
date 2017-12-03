package com.example.islammahoud.ebsfnub.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.L;
import com.example.islammahoud.ebsfnub.R;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainFragment extends Fragment implements MaterialTabListener {


    private MaterialTabHost mTabHost;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;
    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_main2, container, false);

        return v;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        // int icons[]={R.drawable.icon_calendar,R.drawable.icon_speaker,R.drawable.sponsor2,R.drawable.icon_feedback,R.drawable.aboutus,R.drawable.aboutus};
        String[] titles = {"Day 1","Day 2","Day 3","Day 4","Day 5","Day 6","Workshops"};

        FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        public Fragment getItem(int num) {
            Fragment fragment = null;
            L.m("getItem called for " + num);
            switch (num) {
                case 0:
                    fragment = ComitteeFragment.newInstance(1);
                    break;
                case 1:
                    fragment = ComitteeFragment.newInstance(2);
                    break;
                case 2:
                    fragment = ComitteeFragment.newInstance(3);
                    break;
                case 3:
                    fragment = ComitteeFragment.newInstance(4);
                    break;
                case 4:
                    fragment = ComitteeFragment.newInstance(5);
                    break;
                case 5:
                    fragment = ComitteeFragment.newInstance(6);
                    break;
                case 6:
                    fragment = ComitteeFragment.newInstance(7);
                    //  getSupportFragmentManager().beginTransaction().replace(R.id.container, new LecturesFragment()).commit();

                    break;
            }
            return fragment;

        }

        @Override
        public int getCount() {
            return 7;
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
